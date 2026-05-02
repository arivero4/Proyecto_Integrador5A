package negocioA;

import modeloA.Lote;
import persistenciaA.LoteRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar lotes.
 */
public class LoteService {
    private LoteRepository repositorio = new LoteRepository();
    private LugarProduccionService lugarProduccionService = new LugarProduccionService();
    
    public Lote crear(String codigoIca, String numeroLote, String descripcion, float extension, 
                     String lugarProduccionId, String cultivoId) {
        // Validar que no exista el código ICA
        if (repositorio.buscarPorCodigoIca(codigoIca) != null) {
            throw new IllegalArgumentException("Ya existe un lote con código ICA: " + codigoIca);
        }
        
        // Validar que exista el lugar de producción
        lugarProduccionService.buscarPorCodigoIca(lugarProduccionId);
        
        Lote lote = new Lote();
        lote.setCodigoIca(codigoIca);
        lote.setNumeroLote(numeroLote);
        lote.setDescripcion(descripcion);
        lote.setExtension(extension);
        lote.setLugarProduccionId(lugarProduccionId);
        lote.setCultivoId(cultivoId);
        
        // Agregar el lote al lugar de producción
        lugarProduccionService.agregarLote(lugarProduccionId, codigoIca);
        
        return repositorio.guardar(lote);
    }
    
    public Lote buscarPorCodigoIca(String codigoIca) {
        Lote lote = repositorio.buscarPorCodigoIca(codigoIca);
        if (lote == null) {
            throw new NoSuchElementException("No se encontró lote con código ICA: " + codigoIca);
        }
        return lote;
    }
    
    public List<Lote> buscarPorLugarProduccion(String lugarProduccionId) {
        return repositorio.buscarPorLugarProduccion(lugarProduccionId);
    }
    
    public List<Lote> buscarPorCultivo(String cultivoId) {
        return repositorio.buscarPorCultivo(cultivoId);
    }
    
    public List<Lote> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public Lote actualizar(String codigoIca, String numeroLote, String descripcion, 
                          Float extension, String cultivoId) {
        Lote lote = buscarPorCodigoIca(codigoIca);
        if (numeroLote != null) {
            lote.setNumeroLote(numeroLote);
        }
        if (descripcion != null) {
            lote.setDescripcion(descripcion);
        }
        if (extension != null) {
            lote.setExtension(extension);
        }
        if (cultivoId != null) {
            lote.setCultivoId(cultivoId);
        }
        return repositorio.guardar(lote);
    }
    
    public void eliminar(String codigoIca) {
        Lote lote = buscarPorCodigoIca(codigoIca);
        repositorio.eliminar(codigoIca);
    }
    
    public float calcularExtensionTotal(String lugarProduccionId) {
        List<Lote> lotes = buscarPorLugarProduccion(lugarProduccionId);
        float total = 0;
        for (Lote lote : lotes) {
            total += lote.getExtension();
        }
        return total;
    }
}
