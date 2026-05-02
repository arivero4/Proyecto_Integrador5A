package presentacionB;

import modeloB.ResultadoTecnico;
import negocioB.ResultadoTecnicoService;
import java.util.*;

/**
 * Controlador REST para gestionar resultados técnicos.
 * Puerto: 8083
 */
public class ResultadoTecnicoController {
    private ResultadoTecnicoService servicio = new ResultadoTecnicoService();
    
    /**
     * POST /api/resultados-tecnicos
     * Crear nuevo resultado técnico
     */
    public ResultadoTecnico crear(String codigoIca, String descripcion, String diagnostico,
                                 String recomendacion, String inspeccionFitosanitariaId) {
        try {
            return servicio.crear(codigoIca, descripcion, diagnostico, recomendacion, inspeccionFitosanitariaId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/resultados-tecnicos/{codigoIca}
     * Buscar resultado técnico por código ICA
     */
    public ResultadoTecnico buscarPorCodigoIca(String codigoIca) {
        try {
            return servicio.buscarPorCodigoIca(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Resultado técnico no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/resultados-tecnicos/inspeccion/{inspeccionFitosanitariaId}
     * Buscar resultados técnicos por inspección
     */
    public List<ResultadoTecnico> buscarPorInspeccion(String inspeccionFitosanitariaId) {
        return servicio.buscarPorInspeccion(inspeccionFitosanitariaId);
    }
    
    /**
     * GET /api/resultados-tecnicos
     * Listar todos los resultados técnicos
     */
    public List<ResultadoTecnico> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * PUT /api/resultados-tecnicos/{codigoIca}
     * Actualizar resultado técnico
     */
    public ResultadoTecnico actualizar(String codigoIca, String descripcion, String diagnostico,
                                      String recomendacion) {
        try {
            return servicio.actualizar(codigoIca, descripcion, diagnostico, recomendacion);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Resultado técnico no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/resultados-tecnicos/{codigoIca}
     * Eliminar resultado técnico
     */
    public void eliminar(String codigoIca) {
        try {
            servicio.eliminar(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Resultado técnico no encontrado: " + e.getMessage());
        }
    }
}
