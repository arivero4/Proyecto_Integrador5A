package negocioC;

import modeloC.AsistenteTecnico;
import persistenciaC.AsistenteTecnicoRepository;
import java.util.List;
import java.util.UUID;

/**
 * Servicio de negocio para gestión de asistentes técnicos.
 * Maneja la lógica de creación, actualización y consulta de asistentes técnicos.
 */
public class AsistenteTecnicoService {
    private AsistenteTecnicoRepository repository;
    
    public AsistenteTecnicoService() {
        this.repository = new AsistenteTecnicoRepository();
    }
    
    // Crear asistente técnico con todos sus datos
    public AsistenteTecnico crearAsistente(String numeroIdentificacion, String rol, String nombre, 
                                          String telefonoContacto, String correoElectronico,
                                          String numeroTarjetaProfesional) {
        // Validar datos obligatorios
        if (numeroIdentificacion == null || numeroIdentificacion.isEmpty()) {
            throw new RuntimeException("Número de identificación es obligatorio");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("Nombre es obligatorio");
        }
        if (numeroTarjetaProfesional == null || numeroTarjetaProfesional.isEmpty()) {
            throw new RuntimeException("Número de tarjeta profesional es obligatorio");
        }
        
        // Validar número de identificación único
        if (repository.buscarPorNumeroIdentificacion(numeroIdentificacion) != null) {
            throw new RuntimeException("El número de identificación ya está registrado");
        }
        
        // Validar tarjeta profesional única
        if (repository.buscarPorTarjetaProfesional(numeroTarjetaProfesional) != null) {
            throw new RuntimeException("El número de tarjeta profesional ya está registrado");
        }
        
        // Crear asistente con ID único
        AsistenteTecnico asistente = new AsistenteTecnico();
        asistente.setId(UUID.randomUUID().toString());
        asistente.setNumeroIdentificacion(numeroIdentificacion);
        asistente.setRol(rol != null ? rol : "ASISTENTE_TECNICO");
        asistente.setNombre(nombre);
        asistente.setTelefonoContacto(telefonoContacto);
        asistente.setCorreoElectronico(correoElectronico);
        asistente.setNumeroTarjetaProfesional(numeroTarjetaProfesional);
        
        return repository.guardar(asistente);
    }
    
    // Obtener asistente por ID
    public AsistenteTecnico obtenerAsistente(String id) {
        AsistenteTecnico asistente = repository.buscarPorId(id);
        if (asistente == null) {
            throw new RuntimeException("Asistente técnico no encontrado");
        }
        return asistente;
    }
    
    // Obtener por número de identificación
    public AsistenteTecnico obtenerPorNumeroIdentificacion(String numeroIdentificacion) {
        AsistenteTecnico asistente = repository.buscarPorNumeroIdentificacion(numeroIdentificacion);
        if (asistente == null) {
            throw new RuntimeException("Asistente no encontrado");
        }
        return asistente;
    }
    
    // Obtener por tarjeta profesional
    public AsistenteTecnico obtenerPorTarjetaProfesional(String numeroTarjeta) {
        AsistenteTecnico asistente = repository.buscarPorTarjetaProfesional(numeroTarjeta);
        if (asistente == null) {
            throw new RuntimeException("Asistente no encontrado");
        }
        return asistente;
    }
    
    // Listar todos los asistentes técnicos
    public List<AsistenteTecnico> listarTodos() {
        return repository.listarTodos();
    }
    
    // Listar asistentes por lugar de producción
    public List<AsistenteTecnico> listarPorLugarProduccion(String lugarProduccionId) {
        return repository.listarPorLugarProduccion(lugarProduccionId);
    }
    
    // Agregar lugar de producción supervisado
    public AsistenteTecnico agregarLugarProduccion(String asistenteId, String lugarProduccionId) {
        AsistenteTecnico asistente = obtenerAsistente(asistenteId);
        asistente.agregarLugarProduccion(lugarProduccionId);
        return repository.guardar(asistente);
    }
    
    // Agregar inspección realizada
    public AsistenteTecnico agregarInspeccion(String asistenteId, String inspeccionId) {
        AsistenteTecnico asistente = obtenerAsistente(asistenteId);
        asistente.agregarInspeccionFitosanitaria(inspeccionId);
        return repository.guardar(asistente);
    }
    
    // Actualizar asistente
    public AsistenteTecnico actualizarAsistente(String id, String nombre, String telefonoContacto, 
                                                String correoElectronico, String numeroTarjetaProfesional) {
        AsistenteTecnico asistente = obtenerAsistente(id);
        if (nombre != null) asistente.setNombre(nombre);
        if (telefonoContacto != null) asistente.setTelefonoContacto(telefonoContacto);
        if (correoElectronico != null) asistente.setCorreoElectronico(correoElectronico);
        if (numeroTarjetaProfesional != null) asistente.setNumeroTarjetaProfesional(numeroTarjetaProfesional);
        return repository.guardar(asistente);
    }
    
    // Eliminar asistente
    public void eliminarAsistente(String id) {
        repository.eliminar(id);
    }
}
