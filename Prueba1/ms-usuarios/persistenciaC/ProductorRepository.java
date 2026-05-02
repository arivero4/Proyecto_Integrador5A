package persistenciaC;

import modeloC.Productor;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de productores.
 * Simula una base de datos en memoria usando un HashMap.
 */
public class ProductorRepository {
    private Map<String, Productor> productores = new HashMap<>();
    
    public Productor guardar(Productor productor) {
        productores.put(productor.getId(), productor);
        return productor;
    }
    
    public Productor buscarPorId(String id) {
        return productores.get(id);
    }
    
    public Productor buscarPorNumeroIdentificacion(String numeroIdentificacion) {
        return productores.values().stream()
            .filter(p -> p.getNumeroIdentificacion() != null && p.getNumeroIdentificacion().equals(numeroIdentificacion))
            .findFirst()
            .orElse(null);
    }
    
    public List<Productor> listarTodos() {
        return new ArrayList<>(productores.values());
    }
    
    public List<Productor> listarPorLugarProduccion(String lugarProduccionId) {
        List<Productor> resultado = new ArrayList<>();
        for (Productor p : productores.values()) {
            if (p.getLugaresProduccionIds().contains(lugarProduccionId)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
    
    public void eliminar(String id) {
        productores.remove(id);
    }
}
