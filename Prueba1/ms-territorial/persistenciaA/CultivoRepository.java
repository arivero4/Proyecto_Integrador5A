package persistenciaA;

import modeloA.Cultivo;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de cultivos.
 */
public class CultivoRepository {
    private Map<String, Cultivo> cultivos = new HashMap<>();
    
    public Cultivo guardar(Cultivo cultivo) {
        cultivos.put(cultivo.getCodigoIca(), cultivo);
        return cultivo;
    }
    
    public Cultivo buscarPorCodigoIca(String codigoIca) {
        return cultivos.get(codigoIca);
    }
    
    public Cultivo buscarPorNombreComun(String nombreComun) {
        for (Cultivo cultivo : cultivos.values()) {
            if (cultivo.getNombreComun() != null && 
                cultivo.getNombreComun().equalsIgnoreCase(nombreComun)) {
                return cultivo;
            }
        }
        return null;
    }
    
    public List<Cultivo> buscarPorNombreCientifico(String nombreCientifico) {
        List<Cultivo> resultado = new ArrayList<>();
        for (Cultivo cultivo : cultivos.values()) {
            if (cultivo.getNombreCientifico() != null && 
                cultivo.getNombreCientifico().equalsIgnoreCase(nombreCientifico)) {
                resultado.add(cultivo);
            }
        }
        return resultado;
    }
    
    public List<Cultivo> listarTodos() {
        return new ArrayList<>(cultivos.values());
    }
    
    public void eliminar(String codigoIca) {
        cultivos.remove(codigoIca);
    }
}
