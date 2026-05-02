package com.example.predios.service;

import com.example.predios.dto.PredioDTO;
import com.example.predios.entity.Predio;
import com.example.predios.enums.EstadoPredio;
import com.example.predios.exception.ResourceNotFoundException;
import com.example.predios.repository.PredioRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PredioServiceTest {

    @Mock
    private PredioRepository predioRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PredioService predioService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(predioService, "usuariosUrl", "http://localhost:8081");
    }

    @Test
    void crearPredio_cuandoProductorExiste_creaElPredio() {
        PredioDTO dto = new PredioDTO(null, 1L, "Finca El Roble", 1L, 10.5, 4.5, -75.0, null);
        Predio saved = new Predio(1L, 1L, "Finca El Roble", 1L, 10.5, 4.5, -75.0, EstadoPredio.ACTIVO);
        when(restTemplate.getForObject(anyString(), eq(Object.class))).thenReturn(new Object());
        when(predioRepository.save(any())).thenReturn(saved);

        PredioDTO result = predioService.crearPredio(dto);

        assertEquals(EstadoPredio.ACTIVO, result.getEstado());
        assertEquals("Finca El Roble", result.getNombre());
    }

    @Test
    void crearPredio_cuandoProductorNoExiste_lanzaResourceNotFoundException() {
        PredioDTO dto = new PredioDTO(null, 99L, "Finca Test", 1L, 5.0, null, null, null);
        when(restTemplate.getForObject(anyString(), eq(Object.class)))
                .thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(ResourceNotFoundException.class, () -> predioService.crearPredio(dto));
        verify(predioRepository, never()).save(any());
    }

    @Test
    void obtenerPredio_cuandoNoExiste_lanzaResourceNotFoundException() {
        when(predioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> predioService.obtenerPredio(99L));
    }

    @Test
    void listarTodos_retornaPagina() {
        Predio p = new Predio(1L, 1L, "Finca", 1L, 5.0, null, null, EstadoPredio.ACTIVO);
        when(predioRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(p)));

        Page<PredioDTO> result = predioService.listarTodos(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void eliminarPredio_cuandoNoExiste_lanzaResourceNotFoundException() {
        when(predioRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> predioService.eliminarPredio(99L));
        verify(predioRepository, never()).deleteById(any());
    }
}
