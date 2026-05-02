package negocioB;

import modeloB.Plaga;
import persistenciaB.PlagaRepository;
import java.util.*;

/**
 * Servicio de negocio para gestionar plagas.
 */
public class PlagaService {
    private PlagaRepository repositorio = new PlagaRepository();
    private InspeccionFitosanitariaService inspeccionService = new InspeccionFitosanitariaService();
    
    public Plaga crear(String codigoIca, String nombreComun, String nombreCientifico,
                      String tipoOrganismo, int numeroPlantasAfectadas, int nivelSeveridad,
                      String inspeccionFitosanitariaId) {
        // Validar que no exista el código ICA
        if (repositorio.buscarPorCodigoIca(codigoIca) != null) {
            throw new IllegalArgumentException("Ya existe una plaga con código ICA: " + codigoIca);
        }
        
        // Validar que exista la inspección
        inspeccionService.buscarPorCodigoIca(inspeccionFitosanitariaId);
        
        // Validar datos básicos
        if (nombreComun == null || nombreComun.isEmpty()) {
            throw new IllegalArgumentException("El nombre común es obligatorio");
        }
        if (numeroPlantasAfectadas < 0) {
            throw new IllegalArgumentException("El número de plantas afectadas no puede ser negativo");
        }
        if (nivelSeveridad < 1 || nivelSeveridad > 5) {
            throw new IllegalArgumentException("El nivel de severidad debe estar entre 1 y 5");
        }
        
        Plaga plaga = new Plaga();
        plaga.setCodigoIca(codigoIca);
        plaga.setNombreComun(nombreComun);
        plaga.setNombreCientifico(nombreCientifico);
        plaga.setTipoOrganismo(tipoOrganismo);
        plaga.setNumeroPlantasAfectadas(numeroPlantasAfectadas);
        plaga.setNivelSeveridad(nivelSeveridad);
        plaga.setInspeccionFitosanitariaId(inspeccionFitosanitariaId);
        
        // Agregar la plaga a la inspección
        inspeccionService.agregarPlaga(inspeccionFitosanitariaId, codigoIca);
        
        return repositorio.guardar(plaga);
    }
    
    public Plaga buscarPorCodigoIca(String codigoIca) {
        Plaga plaga = repositorio.buscarPorCodigoIca(codigoIca);
        if (plaga == null) {
            throw new NoSuchElementException("No se encontró plaga con código ICA: " + codigoIca);
        }
        return plaga;
    }
    
    public List<Plaga> buscarPorInspeccion(String inspeccionFitosanitariaId) {
        return repositorio.buscarPorInspeccion(inspeccionFitosanitariaId);
    }
    
    public List<Plaga> buscarPorNombreComun(String nombreComun) {
        return repositorio.buscarPorNombreComun(nombreComun);
    }
    
    public List<Plaga> buscarPorTipoOrganismo(String tipoOrganismo) {
        return repositorio.buscarPorTipoOrganismo(tipoOrganismo);
    }
    
    public List<Plaga> listarTodos() {
        return repositorio.listarTodos();
    }
    
    public Plaga actualizar(String codigoIca, String nombreComun, String nombreCientifico,
                           String tipoOrganismo, Integer numeroPlantasAfectadas, Integer nivelSeveridad) {
        Plaga plaga = buscarPorCodigoIca(codigoIca);
        if (nombreComun != null && !nombreComun.isEmpty()) {
            plaga.setNombreComun(nombreComun);
        }
        if (nombreCientifico != null) {
            plaga.setNombreCientifico(nombreCientifico);
        }
        if (tipoOrganismo != null) {
            plaga.setTipoOrganismo(tipoOrganismo);
        }
        if (numeroPlantasAfectadas != null && numeroPlantasAfectadas >= 0) {
            plaga.setNumeroPlantasAfectadas(numeroPlantasAfectadas);
        }
        if (nivelSeveridad != null) {
            if (nivelSeveridad < 1 || nivelSeveridad > 5) {
                throw new IllegalArgumentException("El nivel de severidad debe estar entre 1 y 5");
            }
            plaga.setNivelSeveridad(nivelSeveridad);
        }
        return repositorio.guardar(plaga);
    }
    
    public void eliminar(String codigoIca) {
        Plaga plaga = buscarPorCodigoIca(codigoIca);
        repositorio.eliminar(codigoIca);
    }
}
