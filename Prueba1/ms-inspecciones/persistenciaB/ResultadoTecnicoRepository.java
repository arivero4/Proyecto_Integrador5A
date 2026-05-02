package persistenciaB;

import modeloB.ResultadoTecnico;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de resultados técnicos.
 */
public class ResultadoTecnicoRepository {
    private Map<String, ResultadoTecnico> resultados = new HashMap<>();
    
    public ResultadoTecnico guardar(ResultadoTecnico resultado) {
        resultados.put(resultado.getCodigoIca(), resultado);
        return resultado;
    }
    
    public ResultadoTecnico buscarPorCodigoIca(String codigoIca) {
        return resultados.get(codigoIca);
    }
    
    public List<ResultadoTecnico> buscarPorInspeccion(String inspeccionFitosanitariaId) {
        List<ResultadoTecnico> resultadosEncontrados = new ArrayList<>();
        for (ResultadoTecnico resultado : resultados.values()) {
            if (resultado.getInspeccionFitosanitariaId() != null && 
                resultado.getInspeccionFitosanitariaId().equals(inspeccionFitosanitariaId)) {
                resultadosEncontrados.add(resultado);
            }
        }
        return resultadosEncontrados;
    }
    
    public List<ResultadoTecnico> listarTodos() {
        return new ArrayList<>(resultados.values());
    }
    
    public void eliminar(String codigoIca) {
        resultados.remove(codigoIca);
    }
}
