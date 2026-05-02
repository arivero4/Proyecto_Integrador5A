package com.example.inspecciones.controller;

import com.example.inspecciones.dto.InspeccionFitosanitariaDTO;
import com.example.inspecciones.service.InspeccionService;
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
@RequestMapping("/api/inspecciones")
@RequiredArgsConstructor
@Tag(name = "Inspecciones", description = "Gestión de inspecciones fitosanitarias")
public class InspeccionController {

    private final InspeccionService inspeccionService;

    @PostMapping
    @Operation(summary = "Crear inspección (valida predio y asistente)")
    public ResponseEntity<InspeccionFitosanitariaDTO> crearInspeccion(@Valid @RequestBody InspeccionFitosanitariaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inspeccionService.crearInspeccion(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inspección por ID")
    public ResponseEntity<InspeccionFitosanitariaDTO> obtenerInspeccion(@PathVariable Long id) {
        return ResponseEntity.ok(inspeccionService.obtenerInspeccion(id));
    }

    @GetMapping
    @Operation(summary = "Listar inspecciones con paginación")
    public ResponseEntity<Page<InspeccionFitosanitariaDTO>> listarInspecciones(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(inspeccionService.listarTodas(PageRequest.of(page, size, Sort.by("fechaInspeccion").descending())));
    }

    @GetMapping("/predio/{idPredio}")
    @Operation(summary = "Listar inspecciones por predio")
    public ResponseEntity<List<InspeccionFitosanitariaDTO>> listarPorPredio(@PathVariable Long idPredio) {
        return ResponseEntity.ok(inspeccionService.listarPorPredio(idPredio));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inspección")
    public ResponseEntity<InspeccionFitosanitariaDTO> actualizarInspeccion(@PathVariable Long id, @Valid @RequestBody InspeccionFitosanitariaDTO dto) {
        return ResponseEntity.ok(inspeccionService.actualizarInspeccion(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inspección")
    public ResponseEntity<Void> eliminarInspeccion(@PathVariable Long id) {
        inspeccionService.eliminarInspeccion(id);
        return ResponseEntity.noContent().build();
    }
}
