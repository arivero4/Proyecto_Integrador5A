package presentacionA;

import modeloA.LugarProduccion;
import negocioA.LugarProduccionService;
import java.util.*;

/**
 * Controlador REST para gestionar lugares de producción.
 * Puerto: 8082
 */
public class LugarProduccionController {
    private LugarProduccionService servicio = new LugarProduccionService();
    
    /**
     * POST /api/lugares-produccion
     * Crear nuevo lugar de producción
     */
    public LugarProduccion crear(String codigoIca, String productorId, String predioId, String asistenteTecnicoId) {
        try {
            return servicio.crear(codigoIca, productorId, predioId, asistenteTecnicoId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/lugares-produccion/{codigoIca}
     * Buscar lugar de producción por código ICA
     */
    public LugarProduccion buscarPorCodigoIca(String codigoIca) {
        try {
            return servicio.buscarPorCodigoIca(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Lugar de producción no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/lugares-produccion/productor/{productorId}
     * Buscar lugares de producción por productor
     */
    public List<LugarProduccion> buscarPorProductor(String productorId) {
        return servicio.buscarPorProductor(productorId);
    }
    
    /**
     * GET /api/lugares-produccion/asistente/{asistenteTecnicoId}
     * Buscar lugares de producción por asistente técnico
     */
    public List<LugarProduccion> buscarPorAsistenteTecnico(String asistenteTecnicoId) {
        return servicio.buscarPorAsistenteTecnico(asistenteTecnicoId);
    }
    
    /**
     * GET /api/lugares-produccion/predio/{predioId}
     * Buscar lugares de producción por predio
     */
    public List<LugarProduccion> buscarPorPredio(String predioId) {
        return servicio.buscarPorPredio(predioId);
    }
    
    /**
     * GET /api/lugares-produccion
     * Listar todos los lugares de producción
     */
    public List<LugarProduccion> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * PUT /api/lugares-produccion/{codigoIca}
     * Actualizar lugar de producción (asignar nuevo asistente técnico)
     */
    public LugarProduccion actualizar(String codigoIca, String asistenteTecnicoId) {
        try {
            return servicio.actualizar(codigoIca, asistenteTecnicoId);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Lugar de producción no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/lugares-produccion/{codigoIca}
     * Eliminar lugar de producción
     */
    public void eliminar(String codigoIca) {
        try {
            servicio.eliminar(codigoIca);
        } catch (IllegalStateException e) {
            throw new RuntimeException("No se puede eliminar: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Lugar de producción no encontrado: " + e.getMessage());
        }
    }
}
