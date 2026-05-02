package persistenciaA;

import modeloA.Predio;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de predios.
 */
public class PredioRepository {
    private Map<String, Predio> predios = new HashMap<>();
    
    public Predio guardar(Predio predio) {
        predios.put(predio.getNumeroPredial(), predio);
        return predio;
    }
    
    public Predio buscarPorNumeroPredial(String numeroPredial) {
        return predios.get(numeroPredial);
    }
    
    public List<Predio> buscarPorVereda(String veredaId) {
        List<Predio> resultado = new ArrayList<>();
        for (Predio predio : predios.values()) {
            if (predio.getVeredaId() != null && predio.getVeredaId().equals(veredaId)) {
                resultado.add(predio);
            }
        }
        return resultado;
    }
    
    public List<Predio> listarTodos() {
        return new ArrayList<>(predios.values());
    }
    
    public void eliminar(String numeroPredial) {
        predios.remove(numeroPredial);
    }
    
    public boolean existeNumeroPredial(String numeroPredial) {
        return predios.containsKey(numeroPredial);
    }
}
