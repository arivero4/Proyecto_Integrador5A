package presentacionB;

import modeloB.Plaga;
import negocioB.PlagaService;
import java.util.*;

/**
 * Controlador REST para gestionar plagas.
 * Puerto: 8083
 */
public class PlagaController {
    private PlagaService servicio = new PlagaService();
    
    /**
     * POST /api/plagas
     * Crear nueva plaga
     */
    public Plaga crear(String codigoIca, String nombreComun, String nombreCientifico,
                      String tipoOrganismo, int numeroPlantasAfectadas, int nivelSeveridad,
                      String inspeccionFitosanitariaId) {
        try {
            return servicio.crear(codigoIca, nombreComun, nombreCientifico, tipoOrganismo, 
                                 numeroPlantasAfectadas, nivelSeveridad, inspeccionFitosanitariaId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/plagas/{codigoIca}
     * Buscar plaga por código ICA
     */
    public Plaga buscarPorCodigoIca(String codigoIca) {
        try {
            return servicio.buscarPorCodigoIca(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Plaga no encontrada: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/plagas/inspeccion/{inspeccionFitosanitariaId}
     * Buscar plagas por inspección
     */
    public List<Plaga> buscarPorInspeccion(String inspeccionFitosanitariaId) {
        return servicio.buscarPorInspeccion(inspeccionFitosanitariaId);
    }
    
    /**
     * GET /api/plagas/nombre-comun/{nombreComun}
     * Buscar plagas por nombre común
     */
    public List<Plaga> buscarPorNombreComun(String nombreComun) {
        return servicio.buscarPorNombreComun(nombreComun);
    }
    
    /**
     * GET /api/plagas/tipo-organismo/{tipoOrganismo}
     * Buscar plagas por tipo de organismo
     */
    public List<Plaga> buscarPorTipoOrganismo(String tipoOrganismo) {
        return servicio.buscarPorTipoOrganismo(tipoOrganismo);
    }
    
    /**
     * GET /api/plagas
     * Listar todas las plagas
     */
    public List<Plaga> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * PUT /api/plagas/{codigoIca}
     * Actualizar plaga
     */
    public Plaga actualizar(String codigoIca, String nombreComun, String nombreCientifico,
                           String tipoOrganismo, Integer numeroPlantasAfectadas, Integer nivelSeveridad) {
        try {
            return servicio.actualizar(codigoIca, nombreComun, nombreCientifico, tipoOrganismo, 
                                      numeroPlantasAfectadas, nivelSeveridad);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Plaga no encontrada: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en actualización: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/plagas/{codigoIca}
     * Eliminar plaga
     */
    public void eliminar(String codigoIca) {
        try {
            servicio.eliminar(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Plaga no encontrada: " + e.getMessage());
        }
    }
}
