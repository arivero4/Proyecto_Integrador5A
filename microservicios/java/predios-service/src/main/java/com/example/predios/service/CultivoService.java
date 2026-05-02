package com.example.predios.service;

import com.example.predios.entity.Cultivo;
import com.example.predios.dto.CultivoDTO;
import com.example.predios.repository.CultivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CultivoService {

    private final CultivoRepository cultivoRepository;

    public CultivoDTO crearCultivo(CultivoDTO cultivoDTO) {
        Cultivo cultivo = new Cultivo();
        cultivo.setIdPredio(cultivoDTO.getIdPredio());
        cultivo.setTipoCultivo(cultivoDTO.getTipoCultivo());
        cultivo.setAreaHectareas(cultivoDTO.getAreaHectareas());
        cultivo.setFechaSiembra(cultivoDTO.getFechaSiembra());
        cultivo.setFechaCosechaEstimada(cultivoDTO.getFechaCosechaEstimada());
        cultivo.setEstado("SIEMBRA");
        
        Cultivo saved = cultivoRepository.save(cultivo);
        return convertToDTO(saved);
    }

    public Optional<CultivoDTO> obtenerCultivo(Long id) {
        return cultivoRepository.findById(id).map(this::convertToDTO);
    }

    public List<CultivoDTO> listarTodos() {
        return cultivoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CultivoDTO> listarPorPredio(Long idPredio) {
        return cultivoRepository.findByIdPredio(idPredio).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CultivoDTO actualizarCultivo(Long id, CultivoDTO cultivoDTO) {
        Cultivo cultivo = cultivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado"));
        
        cultivo.setTipoCultivo(cultivoDTO.getTipoCultivo());
        cultivo.setAreaHectareas(cultivoDTO.getAreaHectareas());
        cultivo.setFechaSiembra(cultivoDTO.getFechaSiembra());
        cultivo.setFechaCosechaEstimada(cultivoDTO.getFechaCosechaEstimada());
        cultivo.setEstado(cultivoDTO.getEstado());
        
        Cultivo updated = cultivoRepository.save(cultivo);
        return convertToDTO(updated);
    }

    public void eliminarCultivo(Long id) {
        cultivoRepository.deleteById(id);
    }

    private CultivoDTO convertToDTO(Cultivo cultivo) {
        return new CultivoDTO(
                cultivo.getId(),
                cultivo.getIdPredio(),
                cultivo.getTipoCultivo(),
                cultivo.getAreaHectareas(),
                cultivo.getFechaSiembra(),
                cultivo.getFechaCosechaEstimada(),
                cultivo.getEstado()
        );
    }

}
