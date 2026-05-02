package com.example.predios.controller;

import com.example.predios.dto.PredioDTO;
import com.example.predios.service.PredioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predios")
@RequiredArgsConstructor
@Tag(name = "Predios", description = "Gestión de predios agrícolas")
public class PredioController {

    private final PredioService predioService;

    @PostMapping
    @Operation(summary = "Crear predio (valida que el productor exista)")
    public ResponseEntity<PredioDTO> crearPredio(@Valid @RequestBody PredioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(predioService.crearPredio(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener predio por ID")
    public ResponseEntity<PredioDTO> obtenerPredio(@PathVariable Long id) {
        return ResponseEntity.ok(predioService.obtenerPredio(id));
    }

    @GetMapping
    @Operation(summary = "Listar predios con paginación")
    public ResponseEntity<Page<PredioDTO>> listarPredios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(predioService.listarTodos(PageRequest.of(page, size, Sort.by("nombre"))));
    }

    @GetMapping("/productor/{idProductor}")
    @Operation(summary = "Listar predios por productor")
    public ResponseEntity<List<PredioDTO>> listarPorProductor(@PathVariable Long idProductor) {
        return ResponseEntity.ok(predioService.listarPorProductor(idProductor));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar predio")
    public ResponseEntity<PredioDTO> actualizarPredio(@PathVariable Long id, @Valid @RequestBody PredioDTO dto) {
        return ResponseEntity.ok(predioService.actualizarPredio(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar predio")
    public ResponseEntity<Void> eliminarPredio(@PathVariable Long id) {
        predioService.eliminarPredio(id);
        return ResponseEntity.noContent().build();
    }
}
