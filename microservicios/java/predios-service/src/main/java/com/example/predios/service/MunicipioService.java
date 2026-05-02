package com.example.predios.service;

import com.example.predios.entity.Municipio;
import com.example.predios.dto.MunicipioDTO;
import com.example.predios.repository.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private final MunicipioRepository municipioRepository;

    public MunicipioDTO crearMunicipio(MunicipioDTO municipioDTO) {
        Municipio municipio = new Municipio();
        municipio.setNombre(municipioDTO.getNombre());
        municipio.setDepartamento(municipioDTO.getDepartamento());
        municipio.setCodigoDane(municipioDTO.getCodigoDane());
        
        Municipio saved = municipioRepository.save(municipio);
        return convertToDTO(saved);
    }

    public Optional<MunicipioDTO> obtenerMunicipio(Long id) {
        return municipioRepository.findById(id).map(this::convertToDTO);
    }

    public List<MunicipioDTO> listarTodos() {
        return municipioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MunicipioDTO actualizarMunicipio(Long id, MunicipioDTO municipioDTO) {
        Municipio municipio = municipioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
        
        municipio.setDepartamento(municipioDTO.getDepartamento());
        municipio.setCodigoDane(municipioDTO.getCodigoDane());
        
        Municipio updated = municipioRepository.save(municipio);
        return convertToDTO(updated);
    }

    public void eliminarMunicipio(Long id) {
        municipioRepository.deleteById(id);
    }

    private MunicipioDTO convertToDTO(Municipio municipio) {
        return new MunicipioDTO(
                municipio.getId(),
                municipio.getNombre(),
                municipio.getDepartamento(),
                municipio.getCodigoDane()
        );
    }

}
