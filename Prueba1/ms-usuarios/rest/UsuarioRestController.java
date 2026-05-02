package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentacionC.UsuarioController;
import modeloC.Usuario;

import java.util.List;

/**
 * REST Controller para consultas generales de usuarios.
 * Base URL: /api/usuarios
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioRestController {
    
    private final UsuarioController controller;
    
    public UsuarioRestController() {
        this.controller = new UsuarioController();
    }
    
    /**
     * GET /api/usuarios
     * Listar todos los usuarios (productores + asistentes técnicos)
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        try {
            List<Usuario> usuarios = controller.consultarTodos();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/usuarios/healthcheck
     * Verificar estado del microservicio
     */
    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("MS-USUARIOS funcionando correctamente en puerto 8081");
    }
}
