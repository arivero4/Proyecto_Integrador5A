package presentacionA;

import modeloA.Vereda;
import negocioA.VeredaService;
import java.util.*;

/**
 * Controlador REST para gestionar veredas.
 * Puerto: 8082
 */
public class VeredaController {
    private VeredaService servicio = new VeredaService();
    
    /**
     * POST /api/veredas
     * Crear nueva vereda
     */
    public Vereda crear(String codigoDane, String nombreVereda, String municipioId) {
        try {
            return servicio.crear(codigoDane, nombreVereda, municipioId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/veredas/{codigoDane}
     * Buscar vereda por código DANE
     */
    public Vereda buscarPorCodigoDane(String codigoDane) {
        try {
            return servicio.buscarPorCodigoDane(codigoDane);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Vereda no encontrada: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/veredas/municipio/{municipioId}
     * Buscar veredas por municipio
     */
    public List<Vereda> buscarPorMunicipio(String municipioId) {
        return servicio.buscarPorMunicipio(municipioId);
    }
    
    /**
     * GET /api/veredas
     * Listar todas las veredas
     */
    public List<Vereda> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * PUT /api/veredas/{codigoDane}
     * Actualizar vereda
     */
    public Vereda actualizar(String codigoDane, String nombreVereda) {
        try {
            return servicio.actualizar(codigoDane, nombreVereda);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Vereda no encontrada: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/veredas/{codigoDane}
     * Eliminar vereda
     */
    public void eliminar(String codigoDane) {
        try {
            servicio.eliminar(codigoDane);
        } catch (IllegalStateException e) {
            throw new RuntimeException("No se puede eliminar: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Vereda no encontrada: " + e.getMessage());
        }
    }
}
