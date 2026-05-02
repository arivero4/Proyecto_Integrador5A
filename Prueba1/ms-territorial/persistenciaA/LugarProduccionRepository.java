package persistenciaA;

import modeloA.LugarProduccion;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de lugares de producción.
 */
public class LugarProduccionRepository {
    private Map<String, LugarProduccion> lugaresProduccion = new HashMap<>();
    
    public LugarProduccion guardar(LugarProduccion lugar) {
        lugaresProduccion.put(lugar.getCodigoIca(), lugar);
        return lugar;
    }
    
    public LugarProduccion buscarPorCodigoIca(String codigoIca) {
        return lugaresProduccion.get(codigoIca);
    }
    
    public List<LugarProduccion> buscarPorProductor(String productorId) {
        List<LugarProduccion> resultado = new ArrayList<>();
        for (LugarProduccion lugar : lugaresProduccion.values()) {
            if (lugar.getProductorId() != null && lugar.getProductorId().equals(productorId)) {
                resultado.add(lugar);
            }
        }
        return resultado;
    }
    
    public List<LugarProduccion> buscarPorAsistenteTecnico(String asistenteTecnicoId) {
        List<LugarProduccion> resultado = new ArrayList<>();
        for (LugarProduccion lugar : lugaresProduccion.values()) {
            if (lugar.getAsistenteTecnicoId() != null && 
                lugar.getAsistenteTecnicoId().equals(asistenteTecnicoId)) {
                resultado.add(lugar);
            }
        }
        return resultado;
    }
    
    public List<LugarProduccion> buscarPorPredio(String predioId) {
        List<LugarProduccion> resultado = new ArrayList<>();
        for (LugarProduccion lugar : lugaresProduccion.values()) {
            if (lugar.getPredioId() != null && lugar.getPredioId().equals(predioId)) {
                resultado.add(lugar);
            }
        }
        return resultado;
    }
    
    public List<LugarProduccion> listarTodos() {
        return new ArrayList<>(lugaresProduccion.values());
    }
    
    public void eliminar(String codigoIca) {
        lugaresProduccion.remove(codigoIca);
    }
}
