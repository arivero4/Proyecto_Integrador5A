package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentacionA.LugarProduccionController;
import modeloA.LugarProduccion;

import java.util.List;

/**
 * REST Controller para gestión de Lugares de Producción.
 * Base URL: /api/lugares-produccion
 */
@RestController
@RequestMapping("/api/lugares-produccion")
@CrossOrigin(origins = "*")
public class LugarProduccionRestController {
    
    private final LugarProduccionController controller;
    
    public LugarProduccionRestController() {
        this.controller = new LugarProduccionController();
    }
    
    @GetMapping("/{codigoIca}")
    public ResponseEntity<?> obtenerLugarProduccion(@PathVariable String codigoIca) {
        try {
            LugarProduccion lugar = controller.buscarPorCodigoIca(codigoIca);
            return ResponseEntity.ok(lugar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Lugar de producción no encontrado: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<LugarProduccion>> listarLugaresProduccion(
            @RequestParam(required = false) String numeroPredial) {
        try {
            List<LugarProduccion> lugares;
            if (numeroPredial != null && !numeroPredial.isEmpty()) {
                lugares = controller.buscarPorPredio(numeroPredial);
            } else {
                lugares = controller.listarTodos();
            }
            return ResponseEntity.ok(lugares);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> crearLugarProduccion(@RequestBody LugarProduccion lugar) {
        try {
            LugarProduccion nuevo = controller.crear(
                lugar.getCodigoIca(),
                lugar.getNombre(),
                lugar.getAreaProductiva(),
                lugar.getTipoExplotacion(),
                lugar.getNumeroPredialPredio()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear lugar de producción: " + e.getMessage());
        }
    }
    
    @PutMapping("/{codigoIca}")
    public ResponseEntity<?> actualizarLugarProduccion(
            @PathVariable String codigoIca,
            @RequestBody LugarProduccion lugar) {
        try {
            LugarProduccion actualizado = controller.actualizar(
                codigoIca,
                lugar.getNombre(),
                lugar.getAreaProductiva(),
                lugar.getTipoExplotacion()
            );
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar lugar de producción: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{codigoIca}")
    public ResponseEntity<?> eliminarLugarProduccion(@PathVariable String codigoIca) {
        try {
            controller.eliminar(codigoIca);
            return ResponseEntity.ok("Lugar de producción eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar lugar de producción: " + e.getMessage());
        }
    }
}
