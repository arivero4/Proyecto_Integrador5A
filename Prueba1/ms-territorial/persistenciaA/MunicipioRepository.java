package persistenciaA;

import modeloA.Municipio;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de municipios.
 */
public class MunicipioRepository {
    private Map<String, Municipio> municipios = new HashMap<>();
    
    public Municipio guardar(Municipio municipio) {
        municipios.put(municipio.getCodigoDane(), municipio);
        return municipio;
    }
    
    public Municipio buscarPorCodigoDane(String codigoDane) {
        return municipios.get(codigoDane);
    }
    
    public List<Municipio> buscarPorDepartamento(String departamentoId) {
        List<Municipio> resultado = new ArrayList<>();
        for (Municipio municipio : municipios.values()) {
            if (municipio.getDepartamentoId() != null && 
                municipio.getDepartamentoId().equals(departamentoId)) {
                resultado.add(municipio);
            }
        }
        return resultado;
    }
    
    public List<Municipio> listarTodos() {
        return new ArrayList<>(municipios.values());
    }
    
    public void eliminar(String codigoDane) {
        municipios.remove(codigoDane);
    }
}
