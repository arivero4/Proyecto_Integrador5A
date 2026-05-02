package presentacionA;

import modeloA.Predio;
import negocioA.PredioService;
import java.util.*;

/**
 * Controlador REST para gestionar predios.
 * Puerto: 8082
 */
public class PredioController {
    private PredioService servicio = new PredioService();
    
    /**
     * POST /api/predios
     * Crear nuevo predio
     */
    public Predio crear(String numeroPredial, String nombrePredio, String direccion, String veredaId) {
        try {
            return servicio.crear(numeroPredial, nombrePredio, direccion, veredaId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/predios/{numeroPredial}
     * Buscar predio por número predial
     */
    public Predio buscarPorNumeroPredial(String numeroPredial) {
        try {
            return servicio.buscarPorNumeroPredial(numeroPredial);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Predio no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/predios/vereda/{veredaId}
     * Buscar predios por vereda
     */
    public List<Predio> buscarPorVereda(String veredaId) {
        return servicio.buscarPorVereda(veredaId);
    }
    
    /**
     * GET /api/predios
     * Listar todos los predios
     */
    public List<Predio> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * PUT /api/predios/{numeroPredial}
     * Actualizar predio
     */
    public Predio actualizar(String numeroPredial, String nombrePredio, String direccion) {
        try {
            return servicio.actualizar(numeroPredial, nombrePredio, direccion);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Predio no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/predios/{numeroPredial}
     * Eliminar predio
     */
    public void eliminar(String numeroPredial) {
        try {
            servicio.eliminar(numeroPredial);
        } catch (IllegalStateException e) {
            throw new RuntimeException("No se puede eliminar: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Predio no encontrado: " + e.getMessage());
        }
    }
}
