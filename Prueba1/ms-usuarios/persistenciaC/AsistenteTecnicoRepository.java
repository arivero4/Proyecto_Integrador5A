package persistenciaC;

import modeloC.AsistenteTecnico;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de asistentes técnicos.
 * Simula una base de datos en memoria usando un HashMap.
 */
public class AsistenteTecnicoRepository {
    private Map<String, AsistenteTecnico> asistentes = new HashMap<>();
    
    public AsistenteTecnico guardar(AsistenteTecnico asistente) {
        asistentes.put(asistente.getId(), asistente);
        return asistente;
    }
    
    public AsistenteTecnico buscarPorId(String id) {
        return asistentes.get(id);
    }
    
    public AsistenteTecnico buscarPorNumeroIdentificacion(String numeroIdentificacion) {
        return asistentes.values().stream()
            .filter(a -> a.getNumeroIdentificacion() != null && a.getNumeroIdentificacion().equals(numeroIdentificacion))
            .findFirst()
            .orElse(null);
    }
    
    public AsistenteTecnico buscarPorTarjetaProfesional(String numeroTarjeta) {
        return asistentes.values().stream()
            .filter(a -> a.getNumeroTarjetaProfesional() != null && a.getNumeroTarjetaProfesional().equals(numeroTarjeta))
            .findFirst()
            .orElse(null);
    }
    
    public List<AsistenteTecnico> listarTodos() {
        return new ArrayList<>(asistentes.values());
    }
    
    public List<AsistenteTecnico> listarPorLugarProduccion(String lugarProduccionId) {
        List<AsistenteTecnico> resultado = new ArrayList<>();
        for (AsistenteTecnico a : asistentes.values()) {
            if (a.getLugaresProduccionIds().contains(lugarProduccionId)) {
                resultado.add(a);
            }
        }
        return resultado;
    }
    
    public void eliminar(String id) {
        asistentes.remove(id);
    }
}
