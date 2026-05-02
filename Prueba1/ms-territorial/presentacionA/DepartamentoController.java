package presentacionA;

import modeloA.Departamento;
import negocioA.DepartamentoService;
import java.util.*;

/**
 * Controlador REST para gestionar departamentos.
 * Puerto: 8082
 */
public class DepartamentoController {
    private DepartamentoService servicio = new DepartamentoService();
    
    /**
     * POST /api/departamentos
     * Crear nuevo departamento
     */
    public Departamento crear(String codigoDane, String nombreDepartamento) {
        try {
            return servicio.crear(codigoDane, nombreDepartamento);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/departamentos/{codigoDane}
     * Buscar departamento por código DANE
     */
    public Departamento buscarPorCodigoDane(String codigoDane) {
        try {
            return servicio.buscarPorCodigoDane(codigoDane);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Departamento no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/departamentos/buscar?nombre={nombre}
     * Buscar departamento por nombre
     */
    public Departamento buscarPorNombre(String nombre) {
        try {
            return servicio.buscarPorNombre(nombre);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Departamento no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/departamentos
     * Listar todos los departamentos
     */
    public List<Departamento> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * PUT /api/departamentos/{codigoDane}
     * Actualizar departamento
     */
    public Departamento actualizar(String codigoDane, String nombreDepartamento) {
        try {
            return servicio.actualizar(codigoDane, nombreDepartamento);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Departamento no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/departamentos/{codigoDane}
     * Eliminar departamento
     */
    public void eliminar(String codigoDane) {
        try {
            servicio.eliminar(codigoDane);
        } catch (IllegalStateException e) {
            throw new RuntimeException("No se puede eliminar: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Departamento no encontrado: " + e.getMessage());
        }
    }
}
