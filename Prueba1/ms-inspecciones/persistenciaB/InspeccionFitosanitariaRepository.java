package persistenciaB;

import modeloB.InspeccionFitosanitaria;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de inspecciones fitosanitarias.
 */
public class InspeccionFitosanitariaRepository {
    private Map<String, InspeccionFitosanitaria> inspecciones = new HashMap<>();
    
    public InspeccionFitosanitaria guardar(InspeccionFitosanitaria inspeccion) {
        inspecciones.put(inspeccion.getCodigoIca(), inspeccion);
        return inspeccion;
    }
    
    public InspeccionFitosanitaria buscarPorCodigoIca(String codigoIca) {
        return inspecciones.get(codigoIca);
    }
    
    public List<InspeccionFitosanitaria> buscarPorLugarProduccion(String lugarProduccionId) {
        List<InspeccionFitosanitaria> resultado = new ArrayList<>();
        for (InspeccionFitosanitaria inspeccion : inspecciones.values()) {
            if (inspeccion.getLugarProduccionId() != null && 
                inspeccion.getLugarProduccionId().equals(lugarProduccionId)) {
                resultado.add(inspeccion);
            }
        }
        return resultado;
    }
    
    public List<InspeccionFitosanitaria> buscarPorAsistenteTecnico(String asistenteTecnicoId) {
        List<InspeccionFitosanitaria> resultado = new ArrayList<>();
        for (InspeccionFitosanitaria inspeccion : inspecciones.values()) {
            if (inspeccion.getAsistenteTecnicoId() != null && 
                inspeccion.getAsistenteTecnicoId().equals(asistenteTecnicoId)) {
                resultado.add(inspeccion);
            }
        }
        return resultado;
    }
    
    public List<InspeccionFitosanitaria> buscarPorFecha(Date fecha) {
        List<InspeccionFitosanitaria> resultado = new ArrayList<>();
        for (InspeccionFitosanitaria inspeccion : inspecciones.values()) {
            if (inspeccion.getFecha() != null && inspeccion.getFecha().equals(fecha)) {
                resultado.add(inspeccion);
            }
        }
        return resultado;
    }
    
    public List<InspeccionFitosanitaria> listarTodos() {
        return new ArrayList<>(inspecciones.values());
    }
    
    public void eliminar(String codigoIca) {
        inspecciones.remove(codigoIca);
    }
}
