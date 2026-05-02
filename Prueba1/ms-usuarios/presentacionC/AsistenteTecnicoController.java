package presentacionC;

import modeloC.AsistenteTecnico;
import negocioC.AsistenteTecnicoService;
import java.util.List;

/**
 * Controlador REST para gestión de asistentes técnicos
 * Expone endpoints HTTP para operaciones CRUD de asistentes técnicos
 */
public class AsistenteTecnicoController {
    private AsistenteTecnicoService service;
    
    public AsistenteTecnicoController() {
        this.service = new AsistenteTecnicoService();
    }
    
    // POST /api/asistentes
    public AsistenteTecnico crear(String numeroIdentificacion, String rol, String nombre, 
                                  String telefonoContacto, String correoElectronico,
                                  String numeroTarjetaProfesional) {
        try {
            return service.crearAsistente(numeroIdentificacion, rol, nombre, 
                                         telefonoContacto, correoElectronico, numeroTarjetaProfesional);
        } catch (Exception e) {
            System.err.println("Error al crear asistente: " + e.getMessage());
            throw e;
        }
    }
    
    // GET /api/asistentes/{id}
    public AsistenteTecnico obtenerPorId(String id) {
        return service.obtenerAsistente(id);
    }
    
    // GET /api/asistentes/identificacion/{numeroIdentificacion}
    public AsistenteTecnico obtenerPorNumeroIdentificacion(String numeroIdentificacion) {
        return service.obtenerPorNumeroIdentificacion(numeroIdentificacion);
    }
    
    // GET /api/asistentes/tarjeta/{numeroTarjeta}
    public AsistenteTecnico obtenerPorTarjetaProfesional(String numeroTarjeta) {
        return service.obtenerPorTarjetaProfesional(numeroTarjeta);
    }
    
    // GET /api/asistentes
    public List<AsistenteTecnico> listarTodos() {
        return service.listarTodos();
    }
    
    // GET /api/asistentes/lugar-produccion/{lugarProduccionId}
    public List<AsistenteTecnico> listarPorLugarProduccion(String lugarProduccionId) {
        return service.listarPorLugarProduccion(lugarProduccionId);
    }
    
    // POST /api/asistentes/{id}/lugares-produccion
    public AsistenteTecnico agregarLugarProduccion(String asistenteId, String lugarProduccionId) {
        try {
            return service.agregarLugarProduccion(asistenteId, lugarProduccionId);
        } catch (Exception e) {
            System.err.println("Error al agregar lugar de producción: " + e.getMessage());
            throw e;
        }
    }
    
    // POST /api/asistentes/{id}/inspecciones
    public AsistenteTecnico agregarInspeccion(String asistenteId, String inspeccionId) {
        try {
            return service.agregarInspeccion(asistenteId, inspeccionId);
        } catch (Exception e) {
            System.err.println("Error al agregar inspección: " + e.getMessage());
            throw e;
        }
    }
    
    // PUT /api/asistentes/{id}
    public AsistenteTecnico actualizar(String id, String nombre, String telefonoContacto, 
                                       String correoElectronico, String numeroTarjetaProfesional) {
        return service.actualizarAsistente(id, nombre, telefonoContacto, 
                                           correoElectronico, numeroTarjetaProfesional);
    }
    
    // DELETE /api/asistentes/{id}
    public void eliminar(String id) {
        try {
            service.eliminarAsistente(id);
            System.out.println("Asistente técnico eliminado exitosamente: " + id);
        } catch (Exception e) {
            System.err.println("Error al eliminar asistente: " + e.getMessage());
            throw e;
        }
    }
}
