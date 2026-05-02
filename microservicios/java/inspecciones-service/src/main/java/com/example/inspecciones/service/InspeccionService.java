package com.example.inspecciones.service;

import com.example.inspecciones.dto.InspeccionFitosanitariaDTO;
import com.example.inspecciones.entity.InspeccionFitosanitaria;
import com.example.inspecciones.enums.EstadoInspeccion;
import com.example.inspecciones.exception.ResourceNotFoundException;
import com.example.inspecciones.repository.InspeccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InspeccionService {

    private final InspeccionRepository inspeccionRepository;
    private final RestTemplate restTemplate;

    @Value("${microservices.usuarios.url}")
    private String usuariosUrl;

    @Value("${microservices.territorial.url}")
    private String prediosUrl;

    public InspeccionFitosanitariaDTO crearInspeccion(InspeccionFitosanitariaDTO dto) {
        validarPredioExiste(dto.getIdPredio());
        validarAsistenteExiste(dto.getIdAsistente());
        InspeccionFitosanitaria inspeccion = new InspeccionFitosanitaria();
        inspeccion.setIdPredio(dto.getIdPredio());
        inspeccion.setIdAsistente(dto.getIdAsistente());
        inspeccion.setFechaInspeccion(dto.getFechaInspeccion());
        inspeccion.setObservaciones(dto.getObservaciones());
        inspeccion.setEstado(EstadoInspeccion.PENDIENTE);
        return convertToDTO(inspeccionRepository.save(inspeccion));
    }

    public InspeccionFitosanitariaDTO obtenerInspeccion(Long id) {
        return inspeccionRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Inspección con ID " + id + " no encontrada"));
    }

    public List<InspeccionFitosanitariaDTO> listarTodas() {
        return inspeccionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<InspeccionFitosanitariaDTO> listarPorPredio(Long idPredio) {
        return inspeccionRepository.findByIdPredio(idPredio).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public InspeccionFitosanitariaDTO actualizarInspeccion(Long id, InspeccionFitosanitariaDTO dto) {
        InspeccionFitosanitaria inspeccion = inspeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inspección con ID " + id + " no encontrada"));
        inspeccion.setObservaciones(dto.getObservaciones());
        if (dto.getEstado() != null) inspeccion.setEstado(dto.getEstado());
        return convertToDTO(inspeccionRepository.save(inspeccion));
    }

    public void eliminarInspeccion(Long id) {
        if (!inspeccionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Inspección con ID " + id + " no encontrada");
        }
        inspeccionRepository.deleteById(id);
    }

    private void validarPredioExiste(Long idPredio) {
        try {
            restTemplate.getForObject(prediosUrl + "/api/predios/" + idPredio, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("El predio con ID " + idPredio + " no existe");
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo verificar el predio. Asegúrese que el servicio de predios esté activo.");
        }
    }

    private void validarAsistenteExiste(Long idAsistente) {
        try {
            restTemplate.getForObject(usuariosUrl + "/api/usuarios/" + idAsistente, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("El asistente con ID " + idAsistente + " no existe");
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo verificar el asistente. Asegúrese que el servicio de usuarios esté activo.");
        }
    }

    private InspeccionFitosanitariaDTO convertToDTO(InspeccionFitosanitaria i) {
        return new InspeccionFitosanitariaDTO(i.getId(), i.getIdPredio(), i.getIdAsistente(),
                i.getFechaInspeccion(), i.getObservaciones(), i.getEstado());
    }
}
