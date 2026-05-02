package presentacionB;

import modeloB.InspeccionFitosanitaria;
import negocioB.InspeccionFitosanitariaService;
import java.util.*;

/**
 * Controlador REST para gestionar inspecciones fitosanitarias.
 * Puerto: 8083
 */
public class InspeccionFitosanitariaController {
    private InspeccionFitosanitariaService servicio = new InspeccionFitosanitariaService();
    
    /**
     * POST /api/inspecciones-fitosanitarias
     * Crear nueva inspección fitosanitaria
     */
    public InspeccionFitosanitaria crear(String codigoIca, String lugarProduccionId, Date fecha,
                                         String asistenteTecnicoId, int totalPlantasEvaluadas,
                                         int plantasAfectadas) {
        try {
            return servicio.crear(codigoIca, lugarProduccionId, fecha, asistenteTecnicoId, 
                                 totalPlantasEvaluadas, plantasAfectadas);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/inspecciones-fitosanitarias/{codigoIca}
     * Buscar inspección por código ICA
     */
    public InspeccionFitosanitaria buscarPorCodigoIca(String codigoIca) {
        try {
            return servicio.buscarPorCodigoIca(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Inspección no encontrada: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/inspecciones-fitosanitarias/lugar-produccion/{lugarProduccionId}
     * Buscar inspecciones por lugar de producción
     */
    public List<InspeccionFitosanitaria> buscarPorLugarProduccion(String lugarProduccionId) {
        return servicio.buscarPorLugarProduccion(lugarProduccionId);
    }
    
    /**
     * GET /api/inspecciones-fitosanitarias/asistente/{asistenteTecnicoId}
     * Buscar inspecciones por asistente técnico
     */
    public List<InspeccionFitosanitaria> buscarPorAsistenteTecnico(String asistenteTecnicoId) {
        return servicio.buscarPorAsistenteTecnico(asistenteTecnicoId);
    }
    
    /**
     * GET /api/inspecciones-fitosanitarias/fecha/{fecha}
     * Buscar inspecciones por fecha
     */
    public List<InspeccionFitosanitaria> buscarPorFecha(Date fecha) {
        return servicio.buscarPorFecha(fecha);
    }
    
    /**
     * GET /api/inspecciones-fitosanitarias
     * Listar todas las inspecciones
     */
    public List<InspeccionFitosanitaria> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * GET /api/inspecciones-fitosanitarias/{codigoIca}/porcentaje-afectacion
     * Calcular porcentaje de plantas afectadas
     */
    public float calcularPorcentajeAfectacion(String codigoIca) {
        return servicio.calcularPorcentajeAfectacion(codigoIca);
    }
    
    /**
     * PUT /api/inspecciones-fitosanitarias/{codigoIca}
     * Actualizar inspección
     */
    public InspeccionFitosanitaria actualizar(String codigoIca, Integer totalPlantasEvaluadas,
                                             Integer plantasAfectadas) {
        try {
            return servicio.actualizar(codigoIca, totalPlantasEvaluadas, plantasAfectadas);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Inspección no encontrada: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en actualización: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/inspecciones-fitosanitarias/{codigoIca}
     * Eliminar inspección
     */
    public void eliminar(String codigoIca) {
        try {
            servicio.eliminar(codigoIca);
        } catch (IllegalStateException e) {
            throw new RuntimeException("No se puede eliminar: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Inspección no encontrada: " + e.getMessage());
        }
    }
}
