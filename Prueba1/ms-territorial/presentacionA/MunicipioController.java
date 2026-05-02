package presentacionA;

import modeloA.Municipio;
import negocioA.MunicipioService;
import java.util.*;

/**
 * Controlador REST para gestionar municipios.
 * Puerto: 8082
 */
public class MunicipioController {
    private MunicipioService servicio = new MunicipioService();
    
    /**
     * POST /api/municipios
     * Crear nuevo municipio
     */
    public Municipio crear(String codigoDane, String nombreMunicipio, String departamentoId) {
        try {
            return servicio.crear(codigoDane, nombreMunicipio, departamentoId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/municipios/{codigoDane}
     * Buscar municipio por código DANE
     */
    public Municipio buscarPorCodigoDane(String codigoDane) {
        try {
            return servicio.buscarPorCodigoDane(codigoDane);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Municipio no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/municipios/departamento/{departamentoId}
     * Buscar municipios por departamento
     */
    public List<Municipio> buscarPorDepartamento(String departamentoId) {
        return servicio.buscarPorDepartamento(departamentoId);
    }
    
    /**
     * GET /api/municipios
     * Listar todos los municipios
     */
    public List<Municipio> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * PUT /api/municipios/{codigoDane}
     * Actualizar municipio
     */
    public Municipio actualizar(String codigoDane, String nombreMunicipio) {
        try {
            return servicio.actualizar(codigoDane, nombreMunicipio);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Municipio no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/municipios/{codigoDane}
     * Eliminar municipio
     */
    public void eliminar(String codigoDane) {
        try {
            servicio.eliminar(codigoDane);
        } catch (IllegalStateException e) {
            throw new RuntimeException("No se puede eliminar: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Municipio no encontrado: " + e.getMessage());
        }
    }
}
