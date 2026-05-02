package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import presentacionB.InspeccionFitosanitariaController;
import modeloB.InspeccionFitosanitaria;

import java.util.List;

/**
 * REST Controller para gestión de Inspecciones Fitosanitarias.
 * Base URL: /api/inspecciones
 * 
 * Se comunica con MS-TERRITORIAL para validar lugares de producción.
 */
@RestController
@RequestMapping("/api/inspecciones")
@CrossOrigin(origins = "*")
public class InspeccionFitosanitariaRestController {
    
    private final InspeccionFitosanitariaController controller;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public InspeccionFitosanitariaRestController() {
        this.controller = new InspeccionFitosanitariaController();
    }
    
    /**
     * GET /api/inspecciones/{id}
     * Obtener inspección por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInspeccion(@PathVariable String id) {
        try {
            InspeccionFitosanitaria inspeccion = controller.buscarPorId(id);
            return ResponseEntity.ok(inspeccion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Inspección no encontrada: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/inspecciones
     * Listar todas las inspecciones o filtrar por lugar de producción
     */
    @GetMapping
    public ResponseEntity<List<InspeccionFitosanitaria>> listarInspecciones(
            @RequestParam(required = false) String codigoIcaLugar) {
        try {
            List<InspeccionFitosanitaria> inspecciones;
            if (codigoIcaLugar != null && !codigoIcaLugar.isEmpty()) {
                inspecciones = controller.buscarPorLugarProduccion(codigoIcaLugar);
            } else {
                inspecciones = controller.listarTodas();
            }
            return ResponseEntity.ok(inspecciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * POST /api/inspecciones
     * Crear nueva inspección fitosanitaria
     * Body: {
     *   "fechaVisita": "07/03/2026",
     *   "estadoPlaga": "Controlado",
     *   "conceptoTecnico": "Aplicar fertilizante",
     *   "codigoIcaLugarProduccion": "ICA123",
     *   "idAsistenteTecnico": "AT001"
     * }
     */
    @PostMapping
    public ResponseEntity<?> crearInspeccion(@RequestBody InspeccionFitosanitaria inspeccion) {
        try {
            // Validar que existe el lugar de producción en MS-TERRITORIAL
            String urlLugarProduccion = "http://localhost:8082/api/lugares-produccion/" + 
                                       inspeccion.getCodigoIcaLugarProduccion();
            try {
                restTemplate.getForObject(urlLugarProduccion, Object.class);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Lugar de producción no existe en MS-TERRITORIAL: " + 
                              inspeccion.getCodigoIcaLugarProduccion());
            }
            
            // Crear inspección
            InspeccionFitosanitaria nueva = controller.crear(
                inspeccion.getFechaVisita(),
                inspeccion.getEstadoPlaga(),
                inspeccion.getConceptoTecnico(),
                inspeccion.getCodigoIcaLugarProduccion(),
                inspeccion.getIdAsistenteTecnico()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear inspección: " + e.getMessage());
        }
    }
    
    /**
     * PUT /api/inspecciones/{id}
     * Actualizar inspección existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarInspeccion(
            @PathVariable String id,
            @RequestBody InspeccionFitosanitaria inspeccion) {
        try {
            InspeccionFitosanitaria actualizada = controller.actualizar(
                id,
                inspeccion.getFechaVisita(),
                inspeccion.getEstadoPlaga(),
                inspeccion.getConceptoTecnico()
            );
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar inspección: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/inspecciones/{id}
     * Eliminar inspección
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInspeccion(@PathVariable String id) {
        try {
            controller.eliminar(id);
            return ResponseEntity.ok("Inspección eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar inspección: " + e.getMessage());
        }
    }
}
