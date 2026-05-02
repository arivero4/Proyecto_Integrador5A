package com.example.inspecciones.controller;

import com.example.inspecciones.dto.PlagaDTO;
import com.example.inspecciones.service.PlagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/plagas")
@RequiredArgsConstructor
public class PlagaController {

    private final PlagaService plagaService;

    @PostMapping
    public ResponseEntity<PlagaDTO> crearPlaga(@Valid @RequestBody PlagaDTO plagaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plagaService.crearPlaga(plagaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlagaDTO> obtenerPlaga(@PathVariable Long id) {
        return plagaService.obtenerPlaga(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PlagaDTO>> listarPlagas() {
        return ResponseEntity.ok(plagaService.listarTodas());
    }

    @GetMapping("/inspeccion/{idInspeccion}")
    public ResponseEntity<List<PlagaDTO>> listarPorInspeccion(@PathVariable Long idInspeccion) {
        return ResponseEntity.ok(plagaService.listarPorInspeccion(idInspeccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlagaDTO> actualizarPlaga(@PathVariable Long id, @Valid @RequestBody PlagaDTO plagaDTO) {
        return ResponseEntity.ok(plagaService.actualizarPlaga(id, plagaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlaga(@PathVariable Long id) {
        plagaService.eliminarPlaga(id);
        return ResponseEntity.noContent().build();
    }

}
