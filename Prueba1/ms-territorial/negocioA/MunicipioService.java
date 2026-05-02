package negocioA;

import modeloA.Municipio;
import persistenciaA.MunicipioRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar municipios.
 */
public class MunicipioService {
    private MunicipioRepository repositorio = new MunicipioRepository();
    private DepartamentoService departamentoService = new DepartamentoService();
    
    public Municipio crear(String codigoDane, String nombreMunicipio, String departamentoId) {
        // Validar que no exista el código DANE
        if (repositorio.buscarPorCodigoDane(codigoDane) != null) {
            throw new IllegalArgumentException("Ya existe un municipio con código DANE: " + codigoDane);
        }
        
        // Validar que exista el departamento
        departamentoService.buscarPorCodigoDane(departamentoId);
        
        Municipio municipio = new Municipio();
        municipio.setCodigoDane(codigoDane);
        municipio.setNombreMunicipio(nombreMunicipio);
        municipio.setDepartamentoId(departamentoId);
        municipio.setVeredasIds(new ArrayList<>());
        
        // Agregar el municipio al departamento
        departamentoService.agregarMunicipio(departamentoId, codigoDane);
        
        return repositorio.guardar(municipio);
    }
    
    public Municipio buscarPorCodigoDane(String codigoDane) {
        Municipio municipio = repositorio.buscarPorCodigoDane(codigoDane);
        if (municipio == null) {
            throw new NoSuchElementException("No se encontró municipio con código DANE: " + codigoDane);
        }
        return municipio;
    }
    
    public List<Municipio> buscarPorDepartamento(String departamentoId) {
        return repositorio.buscarPorDepartamento(departamentoId);
    }
    
    public List<Municipio> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public Municipio actualizar(String codigoDane, String nombreMunicipio) {
        Municipio municipio = buscarPorCodigoDane(codigoDane);
        if (nombreMunicipio != null) {
            municipio.setNombreMunicipio(nombreMunicipio);
        }
        return repositorio.guardar(municipio);
    }
    
    public void agregarVereda(String codigoDane, String veredaId) {
        Municipio municipio = buscarPorCodigoDane(codigoDane);
        municipio.agregarVereda(veredaId);
        repositorio.guardar(municipio);
    }
    
    public void eliminar(String codigoDane) {
        Municipio municipio = buscarPorCodigoDane(codigoDane);
        if (!municipio.getVeredasIds().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar un municipio con veredas asociadas");
        }
        repositorio.eliminar(codigoDane);
    }
}
