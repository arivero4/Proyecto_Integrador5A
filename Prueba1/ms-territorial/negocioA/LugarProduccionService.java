package negocioA;

import modeloA.LugarProduccion;
import persistenciaA.LugarProduccionRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar lugares de producción.
 */
public class LugarProduccionService {
    private LugarProduccionRepository repositorio = new LugarProduccionRepository();
    private PredioService predioService = new PredioService();
    
    public LugarProduccion crear(String codigoIca, String productorId, String predioId, String asistenteTecnicoId) {
        // Validar que no exista el código ICA
        if (repositorio.buscarPorCodigoIca(codigoIca) != null) {
            throw new IllegalArgumentException("Ya existe un lugar de producción con código ICA: " + codigoIca);
        }
        
        // Validar que exista el predio
        predioService.buscarPorNumeroPredial(predioId);
        
        LugarProduccion lugarProduccion = new LugarProduccion();
        lugarProduccion.setCodigoIca(codigoIca);
        lugarProduccion.setProductorId(productorId);
        lugarProduccion.setPredioId(predioId);
        lugarProduccion.setAsistenteTecnicoId(asistenteTecnicoId);
        lugarProduccion.setLotesIds(new ArrayList<>());
        
        // Agregar el lugar de producción al predio
        predioService.agregarLugarProduccion(predioId, codigoIca);
        
        return repositorio.guardar(lugarProduccion);
    }
    
    public LugarProduccion buscarPorCodigoIca(String codigoIca) {
        LugarProduccion lugarProduccion = repositorio.buscarPorCodigoIca(codigoIca);
        if (lugarProduccion == null) {
            throw new NoSuchElementException("No se encontró lugar de producción con código ICA: " + codigoIca);
        }
        return lugarProduccion;
    }
    
    public List<LugarProduccion> buscarPorProductor(String productorId) {
        return repositorio.buscarPorProductor(productorId);
    }
    
    public List<LugarProduccion> buscarPorAsistenteTecnico(String asistenteTecnicoId) {
        return repositorio.buscarPorAsistenteTecnico(asistenteTecnicoId);
    }
    
    public List<LugarProduccion> buscarPorPredio(String predioId) {
        return repositorio.buscarPorPredio(predioId);
    }
    
    public List<LugarProduccion> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public LugarProduccion actualizar(String codigoIca, String asistenteTecnicoId) {
        LugarProduccion lugarProduccion = buscarPorCodigoIca(codigoIca);
        if (asistenteTecnicoId != null) {
            lugarProduccion.setAsistenteTecnicoId(asistenteTecnicoId);
        }
        return repositorio.guardar(lugarProduccion);
    }
    
    public void agregarLote(String codigoIca, String loteId) {
        LugarProduccion lugarProduccion = buscarPorCodigoIca(codigoIca);
        lugarProduccion.agregarLote(loteId);
        repositorio.guardar(lugarProduccion);
    }
    
    public void eliminar(String codigoIca) {
        LugarProduccion lugarProduccion = buscarPorCodigoIca(codigoIca);
        if (!lugarProduccion.getLotesIds().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar un lugar de producción con lotes asociados");
        }
        repositorio.eliminar(codigoIca);
    }
}
