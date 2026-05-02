package com.example.usuarios.service;

import com.example.usuarios.entity.AsistenteTecnico;
import com.example.usuarios.dto.AsistenteTecnicoDTO;
import com.example.usuarios.repository.AsistenteTecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsistenteTecnicoService {

    private final AsistenteTecnicoRepository asistenteTecnicoRepository;

    public AsistenteTecnicoDTO crearAsistente(AsistenteTecnicoDTO asistenteDTO) {
        AsistenteTecnico asistente = new AsistenteTecnico();
        asistente.setIdUsuario(asistenteDTO.getIdUsuario());
        asistente.setCedula(asistenteDTO.getCedula());
        asistente.setEspecialidad(asistenteDTO.getEspecialidad());
        asistente.setNumeroLicencia(asistenteDTO.getNumeroLicencia());
        asistente.setEstadoLicencia(asistenteDTO.getEstadoLicencia());
        
        AsistenteTecnico saved = asistenteTecnicoRepository.save(asistente);
        return convertToDTO(saved);
    }

    public Optional<AsistenteTecnicoDTO> obtenerAsistente(Long id) {
        return asistenteTecnicoRepository.findById(id).map(this::convertToDTO);
    }

    public List<AsistenteTecnicoDTO> listarTodos() {
        return asistenteTecnicoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AsistenteTecnicoDTO actualizarAsistente(Long id, AsistenteTecnicoDTO asistenteDTO) {
        AsistenteTecnico asistente = asistenteTecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistente no encontrado"));
        
        asistente.setEspecialidad(asistenteDTO.getEspecialidad());
        asistente.setNumeroLicencia(asistenteDTO.getNumeroLicencia());
        asistente.setEstadoLicencia(asistenteDTO.getEstadoLicencia());
        
        AsistenteTecnico updated = asistenteTecnicoRepository.save(asistente);
        return convertToDTO(updated);
    }

    public void eliminarAsistente(Long id) {
        asistenteTecnicoRepository.deleteById(id);
    }

    private AsistenteTecnicoDTO convertToDTO(AsistenteTecnico asistente) {
        return new AsistenteTecnicoDTO(
                asistente.getId(),
                asistente.getIdUsuario(),
                asistente.getCedula(),
                asistente.getEspecialidad(),
                asistente.getNumeroLicencia(),
                asistente.getEstadoLicencia()
        );
    }

}
