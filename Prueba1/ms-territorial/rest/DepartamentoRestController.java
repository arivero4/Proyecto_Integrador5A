package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentacionA.DepartamentoController;
import modeloA.Departamento;

import java.util.List;

/**
 * REST Controller para gestión de Departamentos.
 * Base URL: /api/departamentos
 */
@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "*")
public class DepartamentoRestController {
    
    private final DepartamentoController controller;
    
    public DepartamentoRestController() {
        this.controller = new DepartamentoController();
    }
    
    @GetMapping("/{codigoDane}")
    public ResponseEntity<?> obtenerDepartamento(@PathVariable String codigoDane) {
        try {
            Departamento depto = controller.buscarPorCodigoDane(codigoDane);
            return ResponseEntity.ok(depto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Departamento no encontrado: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Departamento>> listarDepartamentos() {
        try {
            List<Departamento> departamentos = controller.listarTodos();
            return ResponseEntity.ok(departamentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> crearDepartamento(@RequestBody Departamento depto) {
        try {
            Departamento nuevo = controller.crear(
                depto.getCodigoDane(),
                depto.getNombre(),
                depto.getRegion()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear departamento: " + e.getMessage());
        }
    }
}
