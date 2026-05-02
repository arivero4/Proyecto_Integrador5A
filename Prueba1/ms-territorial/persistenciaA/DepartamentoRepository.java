package persistenciaA;

import modeloA.Departamento;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de departamentos.
 */
public class DepartamentoRepository {
    private Map<String, Departamento> departamentos = new HashMap<>();
    
    public Departamento guardar(Departamento departamento) {
        departamentos.put(departamento.getCodigoDane(), departamento);
        return departamento;
    }
    
    public Departamento buscarPorCodigoDane(String codigoDane) {
        return departamentos.get(codigoDane);
    }
    
    public Departamento buscarPorNombre(String nombreDepartamento) {
        return departamentos.values().stream()
            .filter(d -> d.getNombreDepartamento() != null && 
                        d.getNombreDepartamento().equalsIgnoreCase(nombreDepartamento))
            .findFirst()
            .orElse(null);
    }
    
    public List<Departamento> listarTodos() {
        return new ArrayList<>(departamentos.values());
    }
    
    public void eliminar(String codigoDane) {
        departamentos.remove(codigoDane);
    }
    
    public boolean existeCodigoDane(String codigoDane) {
        return departamentos.containsKey(codigoDane);
    }
}
