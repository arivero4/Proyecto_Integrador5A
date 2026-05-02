package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentacionC.AsistenteTecnicoController;
import modeloC.AsistenteTecnico;

import java.util.List;

/**
 * REST Controller para gestión de Asistentes Técnicos.
 * Base URL: /api/asistentes-tecnicos
 */
@RestController
@RequestMapping("/api/asistentes-tecnicos")
@CrossOrigin(origins = "*")
public class AsistenteTecnicoRestController {
    
    private final AsistenteTecnicoController controller;
    
    public AsistenteTecnicoRestController() {
        this.controller = new AsistenteTecnicoController();
    }
    
    /**
     * GET /api/asistentes-tecnicos/{id}
     * Buscar asistente técnico por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAsistenteTecnico(@PathVariable String id) {
        try {
            AsistenteTecnico asistente = controller.buscarPorId(id);
            return ResponseEntity.ok(asistente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Asistente técnico no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/asistentes-tecnicos
     * Listar todos los asistentes técnicos
     */
    @GetMapping
    public ResponseEntity<List<AsistenteTecnico>> listarAsistentesTecnicos() {
        try {
            List<AsistenteTecnico> asistentes = controller.listarTodos();
            return ResponseEntity.ok(asistentes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * POST /api/asistentes-tecnicos
     * Crear nuevo asistente técnico
     * Body: {"documentoIdentidad": "456", "nombres": "Ana", "apellidos": "García", 
     *        "telefono": "555-5678", "especialidad": "Cultivos orgánicos"}
     */
    @PostMapping
    public ResponseEntity<?> crearAsistenteTecnico(@RequestBody AsistenteTecnico asistente) {
        try {
            AsistenteTecnico nuevo = controller.crear(
                asistente.getDocumentoIdentidad(),
                asistente.getNombres(),
                asistente.getApellidos(),
                asistente.getTelefono(),
                asistente.getEspecialidad()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear asistente técnico: " + e.getMessage());
        }
    }
    
    /**
     * PUT /api/asistentes-tecnicos/{id}
     * Actualizar asistente técnico existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAsistenteTecnico(
            @PathVariable String id,
            @RequestBody AsistenteTecnico asistente) {
        try {
            AsistenteTecnico actualizado = controller.actualizar(
                id,
                asistente.getNombres(),
                asistente.getApellidos(),
                asistente.getTelefono(),
                asistente.getEspecialidad()
            );
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar asistente técnico: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/asistentes-tecnicos/{id}
     * Eliminar asistente técnico
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAsistenteTecnico(@PathVariable String id) {
        try {
            controller.eliminar(id);
            return ResponseEntity.ok("Asistente técnico eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar asistente técnico: " + e.getMessage());
        }
    }
}
