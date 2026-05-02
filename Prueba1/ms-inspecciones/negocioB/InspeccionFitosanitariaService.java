package negocioB;

import modeloB.InspeccionFitosanitaria;
import persistenciaB.InspeccionFitosanitariaRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar inspecciones fitosanitarias.
 */
public class InspeccionFitosanitariaService {
    private InspeccionFitosanitariaRepository repositorio = new InspeccionFitosanitariaRepository();
    
    public InspeccionFitosanitaria crear(String codigoIca, String lugarProduccionId, Date fecha,
                                         String asistenteTecnicoId, int totalPlantasEvaluadas,
                                         int plantasAfectadas) {
        // Validar que no exista el código ICA
        if (repositorio.buscarPorCodigoIca(codigoIca) != null) {
            throw new IllegalArgumentException("Ya existe una inspección con código ICA: " + codigoIca);
        }
        
        // Validar datos básicos
        if (totalPlantasEvaluadas <= 0) {
            throw new IllegalArgumentException("El total de plantas evaluadas debe ser mayor a 0");
        }
        if (plantasAfectadas < 0) {
            throw new IllegalArgumentException("Las plantas afectadas no pueden ser negativas");
        }
        if (plantasAfectadas > totalPlantasEvaluadas) {
            throw new IllegalArgumentException("Las plantas afectadas no pueden ser mayores al total evaluado");
        }
        
        InspeccionFitosanitaria inspeccion = new InspeccionFitosanitaria();
        inspeccion.setCodigoIca(codigoIca);
        inspeccion.setLugarProduccionId(lugarProduccionId);
        inspeccion.setFecha(fecha);
        inspeccion.setAsistenteTecnicoId(asistenteTecnicoId);
        inspeccion.setTotalPlantasEvaluadas(totalPlantasEvaluadas);
        inspeccion.setPlantasAfectadas(plantasAfectadas);
        inspeccion.setResultadosTecnicosIds(new ArrayList<>());
        inspeccion.setPlagasIds(new ArrayList<>());
        
        return repositorio.guardar(inspeccion);
    }
    
    public InspeccionFitosanitaria buscarPorCodigoIca(String codigoIca) {
        InspeccionFitosanitaria inspeccion = repositorio.buscarPorCodigoIca(codigoIca);
        if (inspeccion == null) {
            throw new NoSuchElementException("No se encontró inspección con código ICA: " + codigoIca);
        }
        return inspeccion;
    }
    
    public List<InspeccionFitosanitaria> buscarPorLugarProduccion(String lugarProduccionId) {
        return repositorio.buscarPorLugarProduccion(lugarProduccionId);
    }
    
    public List<InspeccionFitosanitaria> buscarPorAsistenteTecnico(String asistenteTecnicoId) {
        return repositorio.buscarPorAsistenteTecnico(asistenteTecnicoId);
    }
    
    public List<InspeccionFitosanitaria> buscarPorFecha(Date fecha) {
        return repositorio.buscarPorFecha(fecha);
    }
    
    public List<InspeccionFitosanitaria> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public InspeccionFitosanitaria actualizar(String codigoIca, Integer totalPlantasEvaluadas,
                                             Integer plantasAfectadas) {
        InspeccionFitosanitaria inspeccion = buscarPorCodigoIca(codigoIca);
        if (totalPlantasEvaluadas != null && totalPlantasEvaluadas > 0) {
            inspeccion.setTotalPlantasEvaluadas(totalPlantasEvaluadas);
        }
        if (plantasAfectadas != null && plantasAfectadas >= 0) {
            if (plantasAfectadas > inspeccion.getTotalPlantasEvaluadas()) {
                throw new IllegalArgumentException("Las plantas afectadas no pueden ser mayores al total evaluado");
            }
            inspeccion.setPlantasAfectadas(plantasAfectadas);
        }
        return repositorio.guardar(inspeccion);
    }
    
    public void agregarResultadoTecnico(String codigoIca, String resultadoTecnicoId) {
        InspeccionFitosanitaria inspeccion = buscarPorCodigoIca(codigoIca);
        inspeccion.agregarResultadoTecnico(resultadoTecnicoId);
        repositorio.guardar(inspeccion);
    }
    
    public void agregarPlaga(String codigoIca, String plagaId) {
        InspeccionFitosanitaria inspeccion = buscarPorCodigoIca(codigoIca);
        inspeccion.agregarPlaga(plagaId);
        repositorio.guardar(inspeccion);
    }
    
    public void eliminar(String codigoIca) {
        InspeccionFitosanitaria inspeccion = buscarPorCodigoIca(codigoIca);
        if (!inspeccion.getResultadosTecnicosIds().isEmpty() || !inspeccion.getPlagasIds().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar una inspección con resultados técnicos o plagas asociadas");
        }
        repositorio.eliminar(codigoIca);
    }
    
    public float calcularPorcentajeAfectacion(String codigoIca) {
        InspeccionFitosanitaria inspeccion = buscarPorCodigoIca(codigoIca);
        if (inspeccion.getTotalPlantasEvaluadas() > 0) {
            return (inspeccion.getPlantasAfectadas() * 100.0f) / inspeccion.getTotalPlantasEvaluadas();
        }
        return 0;
    }
}
