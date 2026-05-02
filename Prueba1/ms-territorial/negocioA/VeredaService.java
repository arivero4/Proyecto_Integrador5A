package negocioA;

import modeloA.Vereda;
import persistenciaA.VeredaRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar veredas.
 */
public class VeredaService {
    private VeredaRepository repositorio = new VeredaRepository();
    private MunicipioService municipioService = new MunicipioService();
    
    public Vereda crear(String codigoDane, String nombreVereda, String municipioId) {
        // Validar que no exista el código DANE
        if (repositorio.buscarPorCodigoDane(codigoDane) != null) {
            throw new IllegalArgumentException("Ya existe una vereda con código DANE: " + codigoDane);
        }
        
        // Validar que exista el municipio
        municipioService.buscarPorCodigoDane(municipioId);
        
        Vereda vereda = new Vereda();
        vereda.setCodigoDane(codigoDane);
        vereda.setNombreVereda(nombreVereda);
        vereda.setMunicipioId(municipioId);
        vereda.setPrediosIds(new ArrayList<>());
        
        // Agregar la vereda al municipio
        municipioService.agregarVereda(municipioId, codigoDane);
        
        return repositorio.guardar(vereda);
    }
    
    public Vereda buscarPorCodigoDane(String codigoDane) {
        Vereda vereda = repositorio.buscarPorCodigoDane(codigoDane);
        if (vereda == null) {
            throw new NoSuchElementException("No se encontró vereda con código DANE: " + codigoDane);
        }
        return vereda;
    }
    
    public List<Vereda> buscarPorMunicipio(String municipioId) {
        return repositorio.buscarPorMunicipio(municipioId);
    }
    
    public List<Vereda> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public Vereda actualizar(String codigoDane, String nombreVereda) {
        Vereda vereda = buscarPorCodigoDane(codigoDane);
        if (nombreVereda != null) {
            vereda.setNombreVereda(nombreVereda);
        }
        return repositorio.guardar(vereda);
    }
    
    public void agregarPredio(String codigoDane, String predioId) {
        Vereda vereda = buscarPorCodigoDane(codigoDane);
        vereda.agregarPredio(predioId);
        repositorio.guardar(vereda);
    }
    
    public void eliminar(String codigoDane) {
        Vereda vereda = buscarPorCodigoDane(codigoDane);
        if (!vereda.getPrediosIds().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar una vereda con predios asociados");
        }
        repositorio.eliminar(codigoDane);
    }
}
