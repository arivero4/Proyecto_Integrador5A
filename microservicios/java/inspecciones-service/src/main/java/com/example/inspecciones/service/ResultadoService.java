package com.example.inspecciones.service;

import com.example.inspecciones.entity.Resultado;
import com.example.inspecciones.dto.ResultadoDTO;
import com.example.inspecciones.repository.ResultadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository resultadoRepository;

    public ResultadoDTO crearResultado(ResultadoDTO resultadoDTO) {
        Resultado resultado = new Resultado();
        resultado.setIdInspeccion(resultadoDTO.getIdInspeccion());
        resultado.setRecomendacion(resultadoDTO.getRecomendacion());
        resultado.setTratamientoSugerido(resultadoDTO.getTratamientoSugerido());
        resultado.setFechaSeguimiento(resultadoDTO.getFechaSeguimiento());
        resultado.setEstado("PENDIENTE");
        
        Resultado saved = resultadoRepository.save(resultado);
        return convertToDTO(saved);
    }

    public Optional<ResultadoDTO> obtenerResultado(Long id) {
        return resultadoRepository.findById(id).map(this::convertToDTO);
    }

    public List<ResultadoDTO> listarTodos() {
        return resultadoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ResultadoDTO> listarPorInspeccion(Long idInspeccion) {
        return resultadoRepository.findByIdInspeccion(idInspeccion).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResultadoDTO actualizarResultado(Long id, ResultadoDTO resultadoDTO) {
        Resultado resultado = resultadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
        
        resultado.setRecomendacion(resultadoDTO.getRecomendacion());
        resultado.setTratamientoSugerido(resultadoDTO.getTratamientoSugerido());
        resultado.setFechaSeguimiento(resultadoDTO.getFechaSeguimiento());
        resultado.setEstado(resultadoDTO.getEstado());
        
        Resultado updated = resultadoRepository.save(resultado);
        return convertToDTO(updated);
    }

    public void eliminarResultado(Long id) {
        resultadoRepository.deleteById(id);
    }

    private ResultadoDTO convertToDTO(Resultado resultado) {
        return new ResultadoDTO(
                resultado.getId(),
                resultado.getIdInspeccion(),
                resultado.getRecomendacion(),
                resultado.getTratamientoSugerido(),
                resultado.getFechaSeguimiento(),
                resultado.getEstado()
        );
    }

}
