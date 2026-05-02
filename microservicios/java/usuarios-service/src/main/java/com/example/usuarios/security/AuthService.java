package com.example.usuarios.security;

import com.example.usuarios.dto.AuthRequest;
import com.example.usuarios.dto.AuthResponse;
import com.example.usuarios.entity.Usuario;
import com.example.usuarios.enums.EstadoUsuario;
import com.example.usuarios.enums.TipoUsuario;
import com.example.usuarios.exception.ResourceNotFoundException;
import com.example.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private static final long TOKEN_EXPIRATION = 86_400_000L;

    public AuthResponse login(AuthRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con email: " + request.getEmail()));

        if (usuario.getPassword() == null || !passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        if (usuario.getEstado() == EstadoUsuario.INACTIVO) {
            throw new IllegalArgumentException("El usuario está inactivo");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getTipoUsuario().name());
        return new AuthResponse(token, usuario.getEmail(), usuario.getTipoUsuario().name(), TOKEN_EXPIRATION);
    }

    public AuthResponse register(AuthRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + request.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre() != null ? request.getNombre() : request.getEmail().split("@")[0]);
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setTipoUsuario(request.getTipoUsuario() != null ? request.getTipoUsuario() : TipoUsuario.PRODUCTOR);
        usuario.setEstado(EstadoUsuario.ACTIVO);

        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getTipoUsuario().name());
        return new AuthResponse(token, usuario.getEmail(), usuario.getTipoUsuario().name(), TOKEN_EXPIRATION);
    }
}
