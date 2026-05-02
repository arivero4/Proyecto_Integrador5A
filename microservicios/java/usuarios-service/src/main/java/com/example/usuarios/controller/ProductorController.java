package com.example.usuarios.controller;

import com.example.usuarios.dto.ProductorDTO;
import com.example.usuarios.service.ProductorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/productores")
@RequiredArgsConstructor
public class ProductorController {

    private final ProductorService productorService;

    @PostMapping
    public ResponseEntity<ProductorDTO> crearProductor(@Valid @RequestBody ProductorDTO productorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productorService.crearProductor(productorDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductorDTO> obtenerProductor(@PathVariable Long id) {
        return productorService.obtenerProductor(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductorDTO>> listarProductores() {
        return ResponseEntity.ok(productorService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductorDTO> actualizarProductor(@PathVariable Long id, @Valid @RequestBody ProductorDTO productorDTO) {
        return ResponseEntity.ok(productorService.actualizarProductor(id, productorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProductor(@PathVariable Long id) {
        productorService.eliminarProductor(id);
        return ResponseEntity.noContent().build();
    }

}
