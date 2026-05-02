package negocioA;

import modeloA.Departamento;
import persistenciaA.DepartamentoRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar departamentos.
 */
public class DepartamentoService {
    private DepartamentoRepository repositorio = new DepartamentoRepository();
    
    public Departamento crear(String codigoDane, String nombreDepartamento) {
        // Validar que no exista el código DANE
        if (repositorio.buscarPorCodigoDane(codigoDane) != null) {
            throw new IllegalArgumentException("Ya existe un departamento con código DANE: " + codigoDane);
        }
        
        Departamento departamento = new Departamento();
        departamento.setCodigoDane(codigoDane);
        departamento.setNombreDepartamento(nombreDepartamento);
        departamento.setMunicipiosIds(new ArrayList<>());
        
        return repositorio.guardar(departamento);
    }
    
    public Departamento buscarPorCodigoDane(String codigoDane) {
        Departamento departamento = repositorio.buscarPorCodigoDane(codigoDane);
        if (departamento == null) {
            throw new NoSuchElementException("No se encontró departamento con código DANE: " + codigoDane);
        }
        return departamento;
    }
    
    public Departamento buscarPorNombre(String nombre) {
        Departamento departamento = repositorio.buscarPorNombre(nombre);
        if (departamento == null) {
            throw new NoSuchElementException("No se encontró departamento con nombre: " + nombre);
        }
        return departamento;
    }
    
    public List<Departamento> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public Departamento actualizar(String codigoDane, String nombreDepartamento) {
        Departamento departamento = buscarPorCodigoDane(codigoDane);
        if (nombreDepartamento != null) {
            departamento.setNombreDepartamento(nombreDepartamento);
        }
        return repositorio.guardar(departamento);
    }
    
    public void agregarMunicipio(String codigoDane, String municipioId) {
        Departamento departamento = buscarPorCodigoDane(codigoDane);
        departamento.agregarMunicipio(municipioId);
        repositorio.guardar(departamento);
    }
    
    public void eliminar(String codigoDane) {
        Departamento departamento = buscarPorCodigoDane(codigoDane);
        if (!departamento.getMunicipiosIds().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar un departamento con municipios asociados");
        }
        repositorio.eliminar(codigoDane);
    }
}
