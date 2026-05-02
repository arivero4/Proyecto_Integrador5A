package com.example.predios.controller;

import com.example.predios.dto.CultivoDTO;
import com.example.predios.service.CultivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cultivos")
@RequiredArgsConstructor
public class CultivoController {

    private final CultivoService cultivoService;

    @PostMapping
    public ResponseEntity<CultivoDTO> crearCultivo(@Valid @RequestBody CultivoDTO cultivoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cultivoService.crearCultivo(cultivoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CultivoDTO> obtenerCultivo(@PathVariable Long id) {
        return cultivoService.obtenerCultivo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CultivoDTO>> listarCultivos() {
        return ResponseEntity.ok(cultivoService.listarTodos());
    }

    @GetMapping("/predio/{idPredio}")
    public ResponseEntity<List<CultivoDTO>> listarPorPredio(@PathVariable Long idPredio) {
        return ResponseEntity.ok(cultivoService.listarPorPredio(idPredio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CultivoDTO> actualizarCultivo(@PathVariable Long id, @Valid @RequestBody CultivoDTO cultivoDTO) {
        return ResponseEntity.ok(cultivoService.actualizarCultivo(id, cultivoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCultivo(@PathVariable Long id) {
        cultivoService.eliminarCultivo(id);
        return ResponseEntity.noContent().build();
    }

}
