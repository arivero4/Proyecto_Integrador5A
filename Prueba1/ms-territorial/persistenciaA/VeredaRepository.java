package persistenciaA;

import modeloA.Vereda;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de veredas.
 */
public class VeredaRepository {
    private Map<String, Vereda> veredas = new HashMap<>();
    
    public Vereda guardar(Vereda vereda) {
        veredas.put(vereda.getCodigoDane(), vereda);
        return vereda;
    }
    
    public Vereda buscarPorCodigoDane(String codigoDane) {
        return veredas.get(codigoDane);
    }
    
    public List<Vereda> buscarPorMunicipio(String municipioId) {
        List<Vereda> resultado = new ArrayList<>();
        for (Vereda vereda : veredas.values()) {
            if (vereda.getMunicipioId() != null && 
                vereda.getMunicipioId().equals(municipioId)) {
                resultado.add(vereda);
            }
        }
        return resultado;
    }
    
    public List<Vereda> listarTodos() {
        return new ArrayList<>(veredas.values());
    }
    
    public void eliminar(String codigoDane) {
        veredas.remove(codigoDane);
    }
}
