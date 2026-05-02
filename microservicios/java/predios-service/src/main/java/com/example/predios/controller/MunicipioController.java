package com.example.predios.controller;

import com.example.predios.dto.MunicipioDTO;
import com.example.predios.service.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;

    @PostMapping
    public ResponseEntity<MunicipioDTO> crearMunicipio(@Valid @RequestBody MunicipioDTO municipioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(municipioService.crearMunicipio(municipioDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipioDTO> obtenerMunicipio(@PathVariable Long id) {
        return municipioService.obtenerMunicipio(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MunicipioDTO>> listarMunicipios() {
        return ResponseEntity.ok(municipioService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MunicipioDTO> actualizarMunicipio(@PathVariable Long id, @Valid @RequestBody MunicipioDTO municipioDTO) {
        return ResponseEntity.ok(municipioService.actualizarMunicipio(id, municipioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMunicipio(@PathVariable Long id) {
        municipioService.eliminarMunicipio(id);
        return ResponseEntity.noContent().build();
    }

}
