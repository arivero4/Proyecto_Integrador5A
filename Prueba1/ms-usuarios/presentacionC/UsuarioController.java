package presentacionC;

import modeloC.Usuario;
import negocioC.UsuarioService;
import java.util.List;

/**
 * Controlador REST para gestión de usuarios (genérico para Productor y AsistenteTecnico)
 * Expone endpoints HTTP para operaciones de consulta y actualización
 * La creación se hace a través de ProductorController y AsistenteTecnicoController
 */
public class UsuarioController {
    private UsuarioService service;
    
    public UsuarioController() {
        this.service = new UsuarioService();
    }
    
    // GET /api/usuarios/{id}
    public Usuario obtenerPorId(String id) {
        return service.obtenerUsuario(id);
    }
    
    // GET /api/usuarios/correo/{correo}
    public Usuario obtenerPorCorreo(String correo) {
        return service.obtenerPorCorreoElectronico(correo);
    }
    
    // GET /api/usuarios/identificacion/{numeroIdentificacion}
    public Usuario obtenerPorNumeroIdentificacion(String numeroIdentificacion) {
        return service.obtenerPorNumeroIdentificacion(numeroIdentificacion);
    }
    
    // GET /api/usuarios
    public List<Usuario> listarTodos() {
        return service.listarTodos();
    }
    
    // GET /api/usuarios/rol/{rol}
    public List<Usuario> listarPorRol(String rol) {
        return service.listarPorRol(rol);
    }
    
    // PUT /api/usuarios/{id}
    public Usuario actualizar(String id, String nombre, String telefonoContacto, String correoElectronico) {
        return service.actualizarUsuario(id, nombre, telefonoContacto, correoElectronico);
    }
    
    // DELETE /api/usuarios/{id}
    public void eliminar(String id) {
        try {
            service.eliminarUsuario(id);
            System.out.println("Usuario eliminado exitosamente: " + id);
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            throw e;
        }
    }
}
