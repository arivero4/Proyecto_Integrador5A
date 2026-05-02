package presentacionA;

import modeloA.Cultivo;
import negocioA.CultivoService;
import java.util.*;

/**
 * Controlador REST para gestionar cultivos.
 * Puerto: 8082
 */
public class CultivoController {
    private CultivoService servicio = new CultivoService();
    
    /**
     * POST /api/cultivos
     * Crear nuevo cultivo
     */
    public Cultivo crear(String codigoIca, String nombreComun, String nombreCientifico,
                        String nombreVariedad, int numeroPlantasSembradas, float areaTotal) {
        try {
            return servicio.crear(codigoIca, nombreComun, nombreCientifico, nombreVariedad, 
                                 numeroPlantasSembradas, areaTotal);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error en creación: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/cultivos/{codigoIca}
     * Buscar cultivo por código ICA
     */
    public Cultivo buscarPorCodigoIca(String codigoIca) {
        try {
            return servicio.buscarPorCodigoIca(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Cultivo no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/cultivos/nombre-comun/{nombreComun}
     * Buscar cultivo por nombre común
     */
    public Cultivo buscarPorNombreComun(String nombreComun) {
        try {
            return servicio.buscarPorNombreComun(nombreComun);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Cultivo no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * GET /api/cultivos/nombre-cientifico/{nombreCientifico}
     * Buscar cultivos por nombre científico
     */
    public List<Cultivo> buscarPorNombreCientifico(String nombreCientifico) {
        return servicio.buscarPorNombreCientifico(nombreCientifico);
    }
    
    /**
     * GET /api/cultivos
     * Listar todos los cultivos
     */
    public List<Cultivo> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * GET /api/cultivos/{codigoIca}/densidad-siembra
     * Calcular densidad de siembra (plantas por hectárea)
     */
    public float calcularDensidadSiembra(String codigoIca) {
        return servicio.calcularDensidadSiembra(codigoIca);
    }
    
    /**
     * PUT /api/cultivos/{codigoIca}
     * Actualizar cultivo
     */
    public Cultivo actualizar(String codigoIca, String nombreComun, String nombreCientifico,
                             String nombreVariedad, Integer numeroPlantasSembradas, Float areaTotal) {
        try {
            return servicio.actualizar(codigoIca, nombreComun, nombreCientifico, nombreVariedad, 
                                      numeroPlantasSembradas, areaTotal);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Cultivo no encontrado: " + e.getMessage());
        }
    }
    
    /**
     * DELETE /api/cultivos/{codigoIca}
     * Eliminar cultivo
     */
    public void eliminar(String codigoIca) {
        try {
            servicio.eliminar(codigoIca);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Cultivo no encontrado: " + e.getMessage());
        }
    }
}
