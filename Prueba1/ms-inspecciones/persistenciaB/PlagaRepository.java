package persistenciaB;

import modeloB.Plaga;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de plagas.
 */
public class PlagaRepository {
    private Map<String, Plaga> plagas = new HashMap<>();
    
    public Plaga guardar(Plaga plaga) {
        plagas.put(plaga.getCodigoIca(), plaga);
        return plaga;
    }
    
    public Plaga buscarPorCodigoIca(String codigoIca) {
        return plagas.get(codigoIca);
    }
    
    public List<Plaga> buscarPorInspeccion(String inspeccionFitosanitariaId) {
        List<Plaga> plagasEncontradas = new ArrayList<>();
        for (Plaga plaga : plagas.values()) {
            if (plaga.getInspeccionFitosanitariaId() != null && 
                plaga.getInspeccionFitosanitariaId().equals(inspeccionFitosanitariaId)) {
                plagasEncontradas.add(plaga);
            }
        }
        return plagasEncontradas;
    }
    
    public List<Plaga> buscarPorNombreComun(String nombreComun) {
        List<Plaga> plagasEncontradas = new ArrayList<>();
        for (Plaga plaga : plagas.values()) {
            if (plaga.getNombreComun() != null && 
                plaga.getNombreComun().equalsIgnoreCase(nombreComun)) {
                plagasEncontradas.add(plaga);
            }
        }
        return plagasEncontradas;
    }
    
    public List<Plaga> buscarPorTipoOrganismo(String tipoOrganismo) {
        List<Plaga> plagasEncontradas = new ArrayList<>();
        for (Plaga plaga : plagas.values()) {
            if (plaga.getTipoOrganismo() != null && 
                plaga.getTipoOrganismo().equalsIgnoreCase(tipoOrganismo)) {
                plagasEncontradas.add(plaga);
            }
        }
        return plagasEncontradas;
    }
    
    public List<Plaga> listarTodos() {
        return new ArrayList<>(plagas.values());
    }
    
    public void eliminar(String codigoIca) {
        plagas.remove(codigoIca);
    }
}
