package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentacionA.CultivoController;
import modeloA.Cultivo;

import java.util.List;

/**
 * REST Controller para gestión de Cultivos.
 * Base URL: /api/cultivos
 */
@RestController
@RequestMapping("/api/cultivos")
@CrossOrigin(origins = "*")
public class CultivoRestController {
    
    private final CultivoController controller;
    
    public CultivoRestController() {
        this.controller = new CultivoController();
    }
    
    @GetMapping("/{codigoIca}")
    public ResponseEntity<?> obtenerCultivo(@PathVariable String codigoIca) {
        try {
            Cultivo cultivo = controller.buscarPorCodigoIca(codigoIca);
            return ResponseEntity.ok(cultivo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cultivo no encontrado: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Cultivo>> listarCultivos(
            @RequestParam(required = false) String codigoIcaLote) {
        try {
            List<Cultivo> cultivos;
            if (codigoIcaLote != null && !codigoIcaLote.isEmpty()) {
                cultivos = controller.buscarPorLote(codigoIcaLote);
            } else {
                cultivos = controller.listarTodos();
            }
            return ResponseEntity.ok(cultivos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> crearCultivo(@RequestBody Cultivo cultivo) {
        try {
            Cultivo nuevo = controller.crear(
                cultivo.getCodigoIca(),
                cultivo.getTipoPlanta(),
                cultivo.getVariedad(),
                cultivo.getNumeroPlantasSembradas(),
                cultivo.getAreaCultivada(),
                cultivo.getCodigoIcaLote()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear cultivo: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{codigoIca}")
    public ResponseEntity<?> eliminarCultivo(@PathVariable String codigoIca) {
        try {
            controller.eliminar(codigoIca);
            return ResponseEntity.ok("Cultivo eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar cultivo: " + e.getMessage());
        }
    }
}
