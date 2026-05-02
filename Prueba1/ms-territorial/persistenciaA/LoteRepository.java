package persistenciaA;

import modeloA.Lote;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de lotes.
 */
public class LoteRepository {
    private Map<String, Lote> lotes = new HashMap<>();
    
    public Lote guardar(Lote lote) {
        lotes.put(lote.getCodigoIca(), lote);
        return lote;
    }
    
    public Lote buscarPorCodigoIca(String codigoIca) {
        return lotes.get(codigoIca);
    }
    
    public List<Lote> buscarPorLugarProduccion(String lugarProduccionId) {
        List<Lote> resultado = new ArrayList<>();
        for (Lote lote : lotes.values()) {
            if (lote.getLugarProduccionId() != null && 
                lote.getLugarProduccionId().equals(lugarProduccionId)) {
                resultado.add(lote);
            }
        }
        return resultado;
    }
    
    public List<Lote> buscarPorCultivo(String cultivoId) {
        List<Lote> resultado = new ArrayList<>();
        for (Lote lote : lotes.values()) {
            if (lote.getCultivoId() != null && lote.getCultivoId().equals(cultivoId)) {
                resultado.add(lote);
            }
        }
        return resultado;
    }
    
    public List<Lote> listarTodos() {
        return new ArrayList<>(lotes.values());
    }
    
    public void eliminar(String codigoIca) {
        lotes.remove(codigoIca);
    }
}
