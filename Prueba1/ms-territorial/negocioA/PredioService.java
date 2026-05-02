package negocioA;

import modeloA.Predio;
import persistenciaA.PredioRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar predios.
 */
public class PredioService {
    private PredioRepository repositorio = new PredioRepository();
    private VeredaService veredaService = new VeredaService();
    
    public Predio crear(String numeroPredial, String nombrePredio, String direccion, String veredaId) {
        // Validar que no exista el número predial
        if (repositorio.existeNumeroPredial(numeroPredial)) {
            throw new IllegalArgumentException("Ya existe un predio con número predial: " + numeroPredial);
        }
        
        // Validar que exista la vereda
        veredaService.buscarPorCodigoDane(veredaId);
        
        Predio predio = new Predio();
        predio.setNumeroPredial(numeroPredial);
        predio.setNombrePredio(nombrePredio);
        predio.setDireccion(direccion);
        predio.setVeredaId(veredaId);
        predio.setLugaresProduccionIds(new ArrayList<>());
        
        // Agregar el predio a la vereda
        veredaService.agregarPredio(veredaId, numeroPredial);
        
        return repositorio.guardar(predio);
    }
    
    public Predio buscarPorNumeroPredial(String numeroPredial) {
        Predio predio = repositorio.buscarPorNumeroPredial(numeroPredial);
        if (predio == null) {
            throw new NoSuchElementException("No se encontró predio con número predial: " + numeroPredial);
        }
        return predio;
    }
    
    public List<Predio> buscarPorVereda(String veredaId) {
        return repositorio.buscarPorVereda(veredaId);
    }
    
    public List<Predio> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public Predio actualizar(String numeroPredial, String nombrePredio, String direccion) {
        Predio predio = buscarPorNumeroPredial(numeroPredial);
        if (nombrePredio != null) {
            predio.setNombrePredio(nombrePredio);
        }
        if (direccion != null) {
            predio.setDireccion(direccion);
        }
        return repositorio.guardar(predio);
    }
    
    public void agregarLugarProduccion(String numeroPredial, String lugarProduccionId) {
        Predio predio = buscarPorNumeroPredial(numeroPredial);
        predio.agregarLugarProduccion(lugarProduccionId);
        repositorio.guardar(predio);
    }
    
    public void eliminar(String numeroPredial) {
        Predio predio = buscarPorNumeroPredial(numeroPredial);
        if (!predio.getLugaresProduccionIds().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar un predio con lugares de producción asociados");
        }
        repositorio.eliminar(numeroPredial);
    }
}
