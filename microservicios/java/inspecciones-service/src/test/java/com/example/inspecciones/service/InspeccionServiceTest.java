package com.example.inspecciones.service;

import com.example.inspecciones.dto.InspeccionFitosanitariaDTO;
import com.example.inspecciones.entity.InspeccionFitosanitaria;
import com.example.inspecciones.enums.EstadoInspeccion;
import com.example.inspecciones.exception.ResourceNotFoundException;
import com.example.inspecciones.repository.InspeccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InspeccionServiceTest {

    @Mock
    private InspeccionRepository inspeccionRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private InspeccionService inspeccionService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(inspeccionService, "usuariosUrl", "http://localhost:8081");
        ReflectionTestUtils.setField(inspeccionService, "prediosUrl", "http://localhost:8082");
    }

    @Test
    void crearInspeccion_cuandoPredioYAsistenteExisten_creaInspeccion() {
        InspeccionFitosanitariaDTO dto = new InspeccionFitosanitariaDTO(null, 1L, 2L, new Date(), "Sin novedad", null);
        InspeccionFitosanitaria saved = new InspeccionFitosanitaria(1L, 1L, 2L, new Date(), "Sin novedad", EstadoInspeccion.PENDIENTE);
        when(restTemplate.getForObject(anyString(), eq(Object.class))).thenReturn(new Object());
        when(inspeccionRepository.save(any())).thenReturn(saved);

        InspeccionFitosanitariaDTO result = inspeccionService.crearInspeccion(dto);

        assertEquals(EstadoInspeccion.PENDIENTE, result.getEstado());
        verify(inspeccionRepository).save(any());
    }

    @Test
    void crearInspeccion_cuandoPredioNoExiste_lanzaResourceNotFoundException() {
        InspeccionFitosanitariaDTO dto = new InspeccionFitosanitariaDTO(null, 99L, 1L, new Date(), null, null);
        when(restTemplate.getForObject(contains("/api/predios/"), eq(Object.class)))
                .thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(ResourceNotFoundException.class, () -> inspeccionService.crearInspeccion(dto));
        verify(inspeccionRepository, never()).save(any());
    }

    @Test
    void obtenerInspeccion_cuandoNoExiste_lanzaResourceNotFoundException() {
        when(inspeccionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> inspeccionService.obtenerInspeccion(99L));
    }

    @Test
    void listarTodas_retornaPagina() {
        InspeccionFitosanitaria i = new InspeccionFitosanitaria(1L, 1L, 2L, new Date(), null, EstadoInspeccion.PENDIENTE);
        when(inspeccionRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(i)));

        Page<InspeccionFitosanitariaDTO> result = inspeccionService.listarTodas(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void eliminarInspeccion_cuandoNoExiste_lanzaResourceNotFoundException() {
        when(inspeccionRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> inspeccionService.eliminarInspeccion(99L));
        verify(inspeccionRepository, never()).deleteById(any());
    }
}
