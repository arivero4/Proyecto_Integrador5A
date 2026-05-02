package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentacionC.ProductorController;
import modeloC.Productor;

import java.util.List;

/**
 * REST Controller para gestión de Productores.
 * Base URL: /api/productores
 */
@RestController
@RequestMapping("/api/productores")
@CrossOrigin(origins = "*")
public class ProductorRestController {
    
    private final ProductorController controller;
    
    public ProductorRestController() {
        this.controller = new ProductorController();
    }
    
    /**
     * GET /api/productores/{id}
     * Buscar productor por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductor(@PathVariable String id) {
        try {
            Productor productor = controller.buscarPorId(id);
            return ResponseEntity.ok(productor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Productor no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/productores
     * Listar todos los productores
     */
    @GetMapping
    public ResponseEntity<List<Productor>> listarProductores() {
        try {
            List<Productor> productores = controller.listarTodos();
            return ResponseEntity.ok(productores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * POST /api/productores
     * Crear nuevo productor
     * Body: {"documentoIdentidad": "123", "nombres": "Juan", "apellidos": "Pérez", "telefono": "555-1234"}
     */
    @PostMapping
    public ResponseEntity<?> crearProductor(@RequestBody Productor productor) {
        try {
            Productor nuevo = controller.crear(
                productor.getDocumentoIdentidad(),
                productor.getNombres(),
                productor.getApellidos(),
                productor.getTelefono()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear productor: " + e.getMessage());
        }
    }
    
    /**
     * PUT /api/productores/{id}
     * Actualizar productor existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProductor(
            @PathVariable String id,
            @RequestBody Productor productor) {
        try {
            Productor actualizado = controller.actualizar(
                id,
                productor.getNombres(),
                productor.getApellidos(),
                productor.getTelefono()
            );
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar productor: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/productores/{id}
     * Eliminar productor
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProductor(@PathVariable String id) {
        try {
            controller.eliminar(id);
            return ResponseEntity.ok("Productor eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar productor: " + e.getMessage());
        }
    }
}
