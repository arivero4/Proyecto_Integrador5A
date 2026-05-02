package com.example.usuarios.service;

import com.example.usuarios.entity.Productor;
import com.example.usuarios.dto.ProductorDTO;
import com.example.usuarios.repository.ProductorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductorService {

    private final ProductorRepository productorRepository;

    public ProductorDTO crearProductor(ProductorDTO productorDTO) {
        Productor productor = new Productor();
        productor.setIdUsuario(productorDTO.getIdUsuario());
        productor.setCedula(productorDTO.getCedula());
        productor.setRazonSocial(productorDTO.getRazonSocial());
        productor.setNumeroHectareas(productorDTO.getNumeroHectareas());
        productor.setCertificaciones(productorDTO.getCertificaciones());
        
        Productor saved = productorRepository.save(productor);
        return convertToDTO(saved);
    }

    public Optional<ProductorDTO> obtenerProductor(Long id) {
        return productorRepository.findById(id).map(this::convertToDTO);
    }

    public List<ProductorDTO> listarTodos() {
        return productorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductorDTO actualizarProductor(Long id, ProductorDTO productorDTO) {
        Productor productor = productorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Productor no encontrado"));
        
        productor.setRazonSocial(productorDTO.getRazonSocial());
        productor.setNumeroHectareas(productorDTO.getNumeroHectareas());
        productor.setCertificaciones(productorDTO.getCertificaciones());
        
        Productor updated = productorRepository.save(productor);
        return convertToDTO(updated);
    }

    public void eliminarProductor(Long id) {
        productorRepository.deleteById(id);
    }

    private ProductorDTO convertToDTO(Productor productor) {
        return new ProductorDTO(
                productor.getId(),
                productor.getIdUsuario(),
                productor.getCedula(),
                productor.getRazonSocial(),
                productor.getNumeroHectareas(),
                productor.getCertificaciones()
        );
    }

}
