package com.example.predios.service;

import com.example.predios.dto.PredioDTO;
import com.example.predios.entity.Predio;
import com.example.predios.enums.EstadoPredio;
import com.example.predios.exception.ResourceNotFoundException;
import com.example.predios.repository.PredioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredioService {

    private final PredioRepository predioRepository;
    private final RestTemplate restTemplate;

    @Value("${microservices.usuarios.url}")
    private String usuariosUrl;

    public PredioDTO crearPredio(PredioDTO dto) {
        validarProductorExiste(dto.getIdProductor());
        Predio predio = new Predio();
        predio.setIdProductor(dto.getIdProductor());
        predio.setNombre(dto.getNombre());
        predio.setIdMunicipio(dto.getIdMunicipio());
        predio.setAreaHectareas(dto.getAreaHectareas());
        predio.setLatitud(dto.getLatitud());
        predio.setLongitud(dto.getLongitud());
        predio.setEstado(EstadoPredio.ACTIVO);
        return convertToDTO(predioRepository.save(predio));
    }

    public PredioDTO obtenerPredio(Long id) {
        return predioRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Predio con ID " + id + " no encontrado"));
    }

    public Page<PredioDTO> listarTodos(Pageable pageable) {
        return predioRepository.findAll(pageable).map(this::convertToDTO);
    }

    public List<PredioDTO> listarPorProductor(Long idProductor) {
        return predioRepository.findByIdProductor(idProductor).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PredioDTO actualizarPredio(Long id, PredioDTO dto) {
        Predio predio = predioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Predio con ID " + id + " no encontrado"));
        predio.setNombre(dto.getNombre());
        predio.setAreaHectareas(dto.getAreaHectareas());
        predio.setLatitud(dto.getLatitud());
        predio.setLongitud(dto.getLongitud());
        if (dto.getEstado() != null) predio.setEstado(dto.getEstado());
        return convertToDTO(predioRepository.save(predio));
    }

    public void eliminarPredio(Long id) {
        if (!predioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Predio con ID " + id + " no encontrado");
        }
        predioRepository.deleteById(id);
    }

    private void validarProductorExiste(Long idProductor) {
        try {
            restTemplate.getForObject(usuariosUrl + "/api/usuarios/" + idProductor, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("El productor con ID " + idProductor + " no existe");
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo verificar el productor. Asegúrese que el servicio de usuarios esté activo.");
        }
    }

    private PredioDTO convertToDTO(Predio p) {
        return new PredioDTO(p.getId(), p.getIdProductor(), p.getNombre(),
                p.getIdMunicipio(), p.getAreaHectareas(), p.getLatitud(), p.getLongitud(), p.getEstado());
    }
}
