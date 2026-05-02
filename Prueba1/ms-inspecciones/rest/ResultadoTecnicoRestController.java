package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentacionB.ResultadoTecnicoController;
import modeloB.ResultadoTecnico;

import java.util.List;

/**
 * REST Controller para gestión de Resultados Técnicos.
 * Base URL: /api/resultados-tecnicos
 */
@RestController
@RequestMapping("/api/resultados-tecnicos")
@CrossOrigin(origins = "*")
public class ResultadoTecnicoRestController {
    
    private final ResultadoTecnicoController controller;
    
    public ResultadoTecnicoRestController() {
        this.controller = new ResultadoTecnicoController();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerResultado(@PathVariable String id) {
        try {
            ResultadoTecnico resultado = controller.buscarPorId(id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Resultado técnico no encontrado: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ResultadoTecnico>> listarResultados(
            @RequestParam(required = false) String idInspeccion) {
        try {
            List<ResultadoTecnico> resultados;
            if (idInspeccion != null && !idInspeccion.isEmpty()) {
                resultados = controller.buscarPorInspeccion(idInspeccion);
            } else {
                resultados = controller.listarTodos();
            }
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> crearResultado(@RequestBody ResultadoTecnico resultado) {
        try {
            ResultadoTecnico nuevo = controller.crear(
                resultado.getObservacion(),
                resultado.getRecomendacion(),
                resultado.getIdInspeccion()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear resultado técnico: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarResultado(
            @PathVariable String id,
            @RequestBody ResultadoTecnico resultado) {
        try {
            ResultadoTecnico actualizado = controller.actualizar(
                id,
                resultado.getObservacion(),
                resultado.getRecomendacion()
            );
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar resultado: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarResultado(@PathVariable String id) {
        try {
            controller.eliminar(id);
            return ResponseEntity.ok("Resultado técnico eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar resultado: " + e.getMessage());
        }
    }
}
