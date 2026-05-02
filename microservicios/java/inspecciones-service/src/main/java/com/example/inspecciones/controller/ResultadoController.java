package com.example.inspecciones.controller;

import com.example.inspecciones.dto.ResultadoDTO;
import com.example.inspecciones.service.ResultadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/resultados")
@RequiredArgsConstructor
public class ResultadoController {

    private final ResultadoService resultadoService;

    @PostMapping
    public ResponseEntity<ResultadoDTO> crearResultado(@Valid @RequestBody ResultadoDTO resultadoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resultadoService.crearResultado(resultadoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoDTO> obtenerResultado(@PathVariable Long id) {
        return resultadoService.obtenerResultado(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ResultadoDTO>> listarResultados() {
        return ResponseEntity.ok(resultadoService.listarTodos());
    }

    @GetMapping("/inspeccion/{idInspeccion}")
    public ResponseEntity<List<ResultadoDTO>> listarPorInspeccion(@PathVariable Long idInspeccion) {
        return ResponseEntity.ok(resultadoService.listarPorInspeccion(idInspeccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultadoDTO> actualizarResultado(@PathVariable Long id, @Valid @RequestBody ResultadoDTO resultadoDTO) {
        return ResponseEntity.ok(resultadoService.actualizarResultado(id, resultadoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResultado(@PathVariable Long id) {
        resultadoService.eliminarResultado(id);
        return ResponseEntity.noContent().build();
    }

}
