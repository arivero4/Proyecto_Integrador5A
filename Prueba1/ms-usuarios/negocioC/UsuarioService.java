package negocioC;

import modeloC.Usuario;
import persistenciaC.UsuarioRepository;
import java.util.List;

/**
 * Servicio para gestión general de usuarios (Productor y AsistenteTecnico).
 * Como Usuario es abstracta, la creación de instancias específicas se hace en ProductorService y AsistenteTecnicoService.
 */
public class UsuarioService {
    private UsuarioRepository repository;
    
    public UsuarioService() {
        this.repository = new UsuarioRepository();
    }
    
    // Obtener usuario por ID
    public Usuario obtenerUsuario(String id) {
        Usuario usuario = repository.buscarPorId(id);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuario;
    }
    
    // Obtener por correo electrónico
    public Usuario obtenerPorCorreoElectronico(String correo) {
        Usuario usuario = repository.buscarPorCorreoElectronico(correo);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuario;
    }
    
    // Obtener por número de identificación
    public Usuario obtenerPorNumeroIdentificacion(String numeroIdentificacion) {
        Usuario usuario = repository.buscarPorNumeroIdentificacion(numeroIdentificacion);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuario;
    }
    
    // Listar todos
    public List<Usuario> listarTodos() {
        return repository.listarTodos();
    }
    
    // Listar por rol
    public List<Usuario> listarPorRol(String rol) {
        return repository.listarPorRol(rol);
    }
    
    // Actualizar información básica del usuario
    public Usuario actualizarUsuario(String id, String nombre, String telefonoContacto, String correoElectronico) {
        Usuario usuario = obtenerUsuario(id);
        if (nombre != null) usuario.setNombre(nombre);
        if (telefonoContacto != null) usuario.setTelefonoContacto(telefonoContacto);
        if (correoElectronico != null) usuario.setCorreoElectronico(correoElectronico);
        return repository.guardar(usuario);
    }
    
    // Eliminar usuario
    public void eliminarUsuario(String id) {
        if (!repository.existeCorreoElectronico(obtenerUsuario(id).getCorreoElectronico())) {
            throw new RuntimeException("Usuario no existe");
        }
        repository.eliminar(id);
    }
    
    // Validar correo único
    public boolean existeCorreoElectronico(String correo) {
        return repository.existeCorreoElectronico(correo);
    }
}
