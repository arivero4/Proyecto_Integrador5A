package persistenciaC;

import modeloC.Usuario;
import java.util.*;

/**
 * Repositorio para gestionar la persistencia de usuarios (Productor y AsistenteTecnico).
 * Simula una base de datos en memoria usando un HashMap.
 */
public class UsuarioRepository {
    private Map<String, Usuario> usuarios = new HashMap<>();
    
    // Guardar usuario
    public Usuario guardar(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }
    
    // Buscar por ID
    public Usuario buscarPorId(String id) {
        return usuarios.get(id);
    }
    
    // Buscar por correo electrónico
    public Usuario buscarPorCorreoElectronico(String correo) {
        return usuarios.values().stream()
            .filter(u -> u.getCorreoElectronico() != null && u.getCorreoElectronico().equals(correo))
            .findFirst()
            .orElse(null);
    }
    
    // Buscar por número de identificación
    public Usuario buscarPorNumeroIdentificacion(String numeroIdentificacion) {
        return usuarios.values().stream()
            .filter(u -> u.getNumeroIdentificacion() != null && u.getNumeroIdentificacion().equals(numeroIdentificacion))
            .findFirst()
            .orElse(null);
    }
    
    // Listar todos
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }
    
    // Listar por rol
    public List<Usuario> listarPorRol(String rol) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : usuarios.values()) {
            if (u.getRol() != null && u.getRol().equalsIgnoreCase(rol)) {
                resultado.add(u);
            }
        }
        return resultado;
    }
    
    // Eliminar
    public void eliminar(String id) {
        usuarios.remove(id);
    }
    
    // Verificar si existe correo electrónico
    public boolean existeCorreoElectronico(String correo) {
        return usuarios.values().stream()
            .anyMatch(u -> u.getCorreoElectronico() != null && u.getCorreoElectronico().equals(correo));
    }
}
