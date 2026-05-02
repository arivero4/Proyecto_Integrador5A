package com.example.inspecciones.service;

import com.example.inspecciones.entity.Plaga;
import com.example.inspecciones.dto.PlagaDTO;
import com.example.inspecciones.repository.PlagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlagaService {

    private final PlagaRepository plagaRepository;

    public PlagaDTO crearPlaga(PlagaDTO plagaDTO) {
        Plaga plaga = new Plaga();
        plaga.setIdInspeccion(plagaDTO.getIdInspeccion());
        plaga.setNombrePlaga(plagaDTO.getNombrePlaga());
        plaga.setTipoPlaga(plagaDTO.getTipoPlaga());
        plaga.setSeveridad(plagaDTO.getSeveridad());
        plaga.setPorcentajeAfectacion(plagaDTO.getPorcentajeAfectacion());
        
        Plaga saved = plagaRepository.save(plaga);
        return convertToDTO(saved);
    }

    public Optional<PlagaDTO> obtenerPlaga(Long id) {
        return plagaRepository.findById(id).map(this::convertToDTO);
    }

    public List<PlagaDTO> listarTodas() {
        return plagaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PlagaDTO> listarPorInspeccion(Long idInspeccion) {
        return plagaRepository.findByIdInspeccion(idInspeccion).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PlagaDTO actualizarPlaga(Long id, PlagaDTO plagaDTO) {
        Plaga plaga = plagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plaga no encontrada"));
        
        plaga.setNombrePlaga(plagaDTO.getNombrePlaga());
        plaga.setTipoPlaga(plagaDTO.getTipoPlaga());
        plaga.setSeveridad(plagaDTO.getSeveridad());
        plaga.setPorcentajeAfectacion(plagaDTO.getPorcentajeAfectacion());
        
        Plaga updated = plagaRepository.save(plaga);
        return convertToDTO(updated);
    }

    public void eliminarPlaga(Long id) {
        plagaRepository.deleteById(id);
    }

    private PlagaDTO convertToDTO(Plaga plaga) {
        return new PlagaDTO(
                plaga.getId(),
                plaga.getIdInspeccion(),
                plaga.getNombrePlaga(),
                plaga.getTipoPlaga(),
                plaga.getSeveridad(),
                plaga.getPorcentajeAfectacion()
        );
    }

}
