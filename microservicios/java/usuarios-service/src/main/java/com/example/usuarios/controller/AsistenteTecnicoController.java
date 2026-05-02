package com.example.usuarios.controller;

import com.example.usuarios.dto.AsistenteTecnicoDTO;
import com.example.usuarios.service.AsistenteTecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/asistentes")
@RequiredArgsConstructor
public class AsistenteTecnicoController {

    private final AsistenteTecnicoService asistenteTecnicoService;

    @PostMapping
    public ResponseEntity<AsistenteTecnicoDTO> crearAsistente(@Valid @RequestBody AsistenteTecnicoDTO asistenteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenteTecnicoService.crearAsistente(asistenteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenteTecnicoDTO> obtenerAsistente(@PathVariable Long id) {
        return asistenteTecnicoService.obtenerAsistente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AsistenteTecnicoDTO>> listarAsistentes() {
        return ResponseEntity.ok(asistenteTecnicoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenteTecnicoDTO> actualizarAsistente(@PathVariable Long id, @Valid @RequestBody AsistenteTecnicoDTO asistenteDTO) {
        return ResponseEntity.ok(asistenteTecnicoService.actualizarAsistente(id, asistenteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsistente(@PathVariable Long id) {
        asistenteTecnicoService.eliminarAsistente(id);
        return ResponseEntity.noContent().build();
    }

}
