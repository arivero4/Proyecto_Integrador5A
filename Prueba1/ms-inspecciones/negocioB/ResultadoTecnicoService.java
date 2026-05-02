package negocioB;

import modeloB.ResultadoTecnico;
import persistenciaB.ResultadoTecnicoRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar resultados técnicos.
 */
public class ResultadoTecnicoService {
    private ResultadoTecnicoRepository repositorio = new ResultadoTecnicoRepository();
    private InspeccionFitosanitariaService inspeccionService = new InspeccionFitosanitariaService();
    
    public ResultadoTecnico crear(String codigoIca, String descripcion, String diagnostico,
                                 String recomendacion, String inspeccionFitosanitariaId) {
        // Validar que no exista el código ICA
        if (repositorio.buscarPorCodigoIca(codigoIca) != null) {
            throw new IllegalArgumentException("Ya existe un resultado técnico con código ICA: " + codigoIca);
        }
        
        // Validar que exista la inspección
        inspeccionService.buscarPorCodigoIca(inspeccionFitosanitariaId);
        
        // Validar datos básicos
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        
        ResultadoTecnico resultado = new ResultadoTecnico();
        resultado.setCodigoIca(codigoIca);
        resultado.setDescripcion(descripcion);
        resultado.setDiagnostico(diagnostico);
        resultado.setRecomendacion(recomendacion);
        resultado.setInspeccionFitosanitariaId(inspeccionFitosanitariaId);
        
        // Agregar el resultado a la inspección
        inspeccionService.agregarResultadoTecnico(inspeccionFitosanitariaId, codigoIca);
        
        return repositorio.guardar(resultado);
    }
    
    public ResultadoTecnico buscarPorCodigoIca(String codigoIca) {
        ResultadoTecnico resultado = repositorio.buscarPorCodigoIca(codigoIca);
        if (resultado == null) {
            throw new NoSuchElementException("No se encontró resultado técnico con código ICA: " + codigoIca);
        }
        return resultado;
    }
    
    public List<ResultadoTecnico> buscarPorInspeccion(String inspeccionFitosanitariaId) {
        return repositorio.buscarPorInspeccion(inspeccionFitosanitariaId);
    }
    
    public List<ResultadoTecnico> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public ResultadoTecnico actualizar(String codigoIca, String descripcion, String diagnostico,
                                      String recomendacion) {
        ResultadoTecnico resultado = buscarPorCodigoIca(codigoIca);
        if (descripcion != null && !descripcion.isEmpty()) {
            resultado.setDescripcion(descripcion);
        }
        if (diagnostico != null) {
            resultado.setDiagnostico(diagnostico);
        }
        if (recomendacion != null) {
            resultado.setRecomendacion(recomendacion);
        }
        return repositorio.guardar(resultado);
    }
    
    public void eliminar(String codigoIca) {
        ResultadoTecnico resultado = buscarPorCodigoIca(codigoIca);
        repositorio.eliminar(codigoIca);
    }
}
