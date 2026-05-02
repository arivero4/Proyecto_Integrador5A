package negocioA;

import modeloA.Cultivo;
import persistenciaA.CultivoRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar cultivos.
 */
public class CultivoService {
    private CultivoRepository repositorio = new CultivoRepository();
    
    public Cultivo crear(String codigoIca, String nombreComun, String nombreCientifico, 
                        String nombreVariedad, int numeroPlantasSembradas, float areaTotal) {
        // Validar que no exista el código ICA
        if (repositorio.buscarPorCodigoIca(codigoIca) != null) {
            throw new IllegalArgumentException("Ya existe un cultivo con código ICA: " + codigoIca);
        }
        
        // Validar datos básicos
        if (nombreComun == null || nombreComun.isEmpty()) {
            throw new IllegalArgumentException("El nombre común es obligatorio");
        }
        if (numeroPlantasSembradas <= 0) {
            throw new IllegalArgumentException("El número de plantas debe ser mayor a 0");
        }
        if (areaTotal <= 0) {
            throw new IllegalArgumentException("El área total debe ser mayor a 0");
        }
        
        Cultivo cultivo = new Cultivo();
        cultivo.setCodigoIca(codigoIca);
        cultivo.setNombreComun(nombreComun);
        cultivo.setNombreCientifico(nombreCientifico);
        cultivo.setNombreVariedad(nombreVariedad);
        cultivo.setNumeroPlantasSembradas(numeroPlantasSembradas);
        cultivo.setAreaTotal(areaTotal);
        
        return repositorio.guardar(cultivo);
    }
    
    public Cultivo buscarPorCodigoIca(String codigoIca) {
        Cultivo cultivo = repositorio.buscarPorCodigoIca(codigoIca);
        if (cultivo == null) {
            throw new NoSuchElementException("No se encontró cultivo con código ICA: " + codigoIca);
        }
        return cultivo;
    }
    
    public Cultivo buscarPorNombreComun(String nombreComun) {
        Cultivo cultivo = repositorio.buscarPorNombreComun(nombreComun);
        if (cultivo == null) {
            throw new NoSuchElementException("No se encontró cultivo con nombre común: " + nombreComun);
        }
        return cultivo;
    }
    
    public List<Cultivo> buscarPorNombreCientifico(String nombreCientifico) {
        return repositorio.buscarPorNombreCientifico(nombreCientifico);
    }
    
    public List<Cultivo> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public Cultivo actualizar(String codigoIca, String nombreComun, String nombreCientifico,
                             String nombreVariedad, Integer numeroPlantasSembradas, Float areaTotal) {
        Cultivo cultivo = buscarPorCodigoIca(codigoIca);
        if (nombreComun != null) {
            cultivo.setNombreComun(nombreComun);
        }
        if (nombreCientifico != null) {
            cultivo.setNombreCientifico(nombreCientifico);
        }
        if (nombreVariedad != null) {
            cultivo.setNombreVariedad(nombreVariedad);
        }
        if (numeroPlantasSembradas != null && numeroPlantasSembradas > 0) {
            cultivo.setNumeroPlantasSembradas(numeroPlantasSembradas);
        }
        if (areaTotal != null && areaTotal > 0) {
            cultivo.setAreaTotal(areaTotal);
        }
        return repositorio.guardar(cultivo);
    }
    
    public void eliminar(String codigoIca) {
        Cultivo cultivo = buscarPorCodigoIca(codigoIca);
        repositorio.eliminar(codigoIca);
    }
    
    public float calcularDensidadSiembra(String codigoIca) {
        Cultivo cultivo = buscarPorCodigoIca(codigoIca);
        if (cultivo.getAreaTotal() > 0) {
            return cultivo.getNumeroPlantasSembradas() / cultivo.getAreaTotal();
        }
        return 0;
    }
}
