package presentacionA;

import modeloA.Lote;
import negocioA.LoteService;
import java.util.*;

/**
 * Controlador REST para gestionar lotes.
 * Puerto: 8082
 */
public class LoteController {
    private LoteService servicio = new LoteService();
    
    /**
     * POST /api/lotes
     * Crear nuevo lote
     */
    public Lote crear(String codigoIca, String numeroLote, String descripcion, float extension,
                     String lugarProduccionId, String cultivoId) {
        try {
            return servicio.crear(codigoIca, numeroLote, descripcion, extension, lugarProduccionId, cultivoId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/lotes/{codigoIca}
     * Buscar lote por código ICA
     */
    public Lote buscarPorCodigoIca(String codigoIca) {
        try {
            return servicio.buscarPorCodigoIca(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Lote no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/lotes/lugar-produccion/{lugarProduccionId}
     * Buscar lotes por lugar de producción
     */
    public List<Lote> buscarPorLugarProduccion(String lugarProduccionId) {
        return servicio.buscarPorLugarProduccion(lugarProduccionId);
    }
    
    /**
     * GET /api/lotes/cultivo/{cultivoId}
     * Buscar lotes por cultivo
     */
    public List<Lote> buscarPorCultivo(String cultivoId) {
        return servicio.buscarPorCultivo(cultivoId);
    }
    
    /**
     * GET /api/lotes
     * Listar todos los lotes
     */
    public List<Lote> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * GET /api/lotes/lugar-produccion/{lugarProduccionId}/extension-total
     * Calcular extensión total de lotes en un lugar de producción
     */
    public float calcularExtensionTotal(String lugarProduccionId) {
        return servicio.calcularExtensionTotal(lugarProduccionId);
    }
    
    /**
     * PUT /api/lotes/{codigoIca}
     * Actualizar lote
     */
    public Lote actualizar(String codigoIca, String numeroLote, String descripcion, 
                          Float extension, String cultivoId) {
        try {
            return servicio.actualizar(codigoIca, numeroLote, descripcion, extension, cultivoId);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Lote no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/lotes/{codigoIca}
     * Eliminar lote
     */
    public void eliminar(String codigoIca) {
        try {
            servicio.eliminar(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Lote no encontrado: " + e.getMessage());
        }
    }
}
