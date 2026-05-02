package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentacionB.PlagaController;
import modeloB.Plaga;

import java.util.List;

/**
 * REST Controller para gestión de Plagas.
 * Base URL: /api/plagas
 */
@RestController
@RequestMapping("/api/plagas")
@CrossOrigin(origins = "*")
public class PlagaRestController {
    
    private final PlagaController controller;
    
    public PlagaRestController() {
        this.controller = new PlagaController();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPlaga(@PathVariable String id) {
        try {
            Plaga plaga = controller.buscarPorId(id);
            return ResponseEntity.ok(plaga);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plaga no encontrada: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Plaga>> listarPlagas(
            @RequestParam(required = false) String idResultado) {
        try {
            List<Plaga> plagas;
            if (idResultado != null && !idResultado.isEmpty()) {
                plagas = controller.buscarPorResultadoTecnico(idResultado);
            } else {
                plagas = controller.listarTodas();
            }
            return ResponseEntity.ok(plagas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> crearPlaga(@RequestBody Plaga plaga) {
        try {
            Plaga nueva = controller.crear(
                plaga.getNombreComun(),
                plaga.getNombreCientifico(),
                plaga.getTipo(),
                plaga.getIdResultadoTecnico()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear plaga: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPlaga(
            @PathVariable String id,
            @RequestBody Plaga plaga) {
        try {
            Plaga actualizada = controller.actualizar(
                id,
                plaga.getNombreComun(),
                plaga.getNombreCientifico(),
                plaga.getTipo()
            );
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar plaga: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPlaga(@PathVariable String id) {
        try {
            controller.eliminar(id);
            return ResponseEntity.ok("Plaga eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar plaga: " + e.getMessage());
        }
    }
}
