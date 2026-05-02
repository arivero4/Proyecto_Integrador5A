package negocioC;

import modeloC.Productor;
import persistenciaC.ProductorRepository;
import java.util.List;
import java.util.UUID;

/**
 * Servicio de negocio para gestión de productores.
 * Maneja la lógica de creación, actualización y consulta de productores.
 */
public class ProductorService {
    private ProductorRepository repository;
    
    public ProductorService() {
        this.repository = new ProductorRepository();
    }
    
    // Crear productor con todos sus datos
    public Productor crearProductor(String numeroIdentificacion, String rol, String nombre, 
                                   String telefonoContacto, String correoElectronico) {
        // Validar datos obligatorios
        if (numeroIdentificacion == null || numeroIdentificacion.isEmpty()) {
            throw new RuntimeException("Número de identificación es obligatorio");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("Nombre es obligatorio");
        }
        
        // Validar número de identificación único
        if (repository.buscarPorNumeroIdentificacion(numeroIdentificacion) != null) {
            throw new RuntimeException("El número de identificación ya está registrado");
        }
        
        // Crear productor con ID único
        Productor productor = new Productor();
        productor.setId(UUID.randomUUID().toString());
        productor.setNumeroIdentificacion(numeroIdentificacion);
        productor.setRol(rol != null ? rol : "PRODUCTOR");
        productor.setNombre(nombre);
        productor.setTelefonoContacto(telefonoContacto);
        productor.setCorreoElectronico(correoElectronico);
        
        return repository.guardar(productor);
    }
    
    // Obtener productor por ID
    public Productor obtenerProductor(String id) {
        Productor productor = repository.buscarPorId(id);
        if (productor == null) {
            throw new RuntimeException("Productor no encontrado");
        }
        return productor;
    }
    
    // Obtener por número de identificación
    public Productor obtenerPorNumeroIdentificacion(String numeroIdentificacion) {
        Productor productor = repository.buscarPorNumeroIdentificacion(numeroIdentificacion);
        if (productor == null) {
            throw new RuntimeException("Productor no encontrado");
        }
        return productor;
    }
    
    // Listar todos los productores
    public List<Productor> listarTodos() {
        return repository.listarTodos();
    }
    
    // Listar productores por lugar de producción
    public List<Productor> listarPorLugarProduccion(String lugarProduccionId) {
        return repository.listarPorLugarProduccion(lugarProduccionId);
    }
    
    // Agregar lugar de producción al productor
    public Productor agregarLugarProduccion(String productorId, String lugarProduccionId) {
        Productor productor = obtenerProductor(productorId);
        productor.agregarLugarProduccion(lugarProduccionId);
        return repository.guardar(productor);
    }
    
    // Actualizar productor
    public Productor actualizarProductor(String id, String nombre, String telefonoContacto, String correoElectronico) {
        Productor productor = obtenerProductor(id);
        if (nombre != null) productor.setNombre(nombre);
        if (telefonoContacto != null) productor.setTelefonoContacto(telefonoContacto);
        if (correoElectronico != null) productor.setCorreoElectronico(correoElectronico);
        return repository.guardar(productor);
    }
    
    // Eliminar productor
    public void eliminarProductor(String id) {
        repository.eliminar(id);
    }
}
