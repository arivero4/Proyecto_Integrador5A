package com.example.usuarios.security;

import com.example.usuarios.dto.AuthRequest;
import com.example.usuarios.dto.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Registro e inicio de sesión con JWT")
public class AuthController {

    private final AuthService authService;

    /** Endpoint informativo — útil al abrir la URL en el navegador */
    @GetMapping
    @Operation(summary = "Información de los endpoints de autenticación")
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("servicio", "MS-USUARIOS — Autenticación JWT");
        info.put("swagger", "http://localhost:8081/swagger-ui.html");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("POST /auth/register", "Registrar usuario nuevo (body: email, password, nombre, tipoUsuario)");
        endpoints.put("POST /auth/login",    "Iniciar sesión (body: email, password) → devuelve token JWT");
        endpoints.put("GET  /auth/me",       "Info del usuario autenticado (requiere Authorization: Bearer <token>)");
        info.put("endpoints", endpoints);

        info.put("nota", "Usar el token JWT en el header: Authorization: Bearer <token>");
        return ResponseEntity.ok(info);
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar nuevo usuario y obtener token JWT")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener token JWT")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    @Operation(summary = "Información del usuario autenticado (requiere token)")
    public ResponseEntity<Map<String, String>> me(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
                "email", authentication.getName(),
                "role",  authentication.getAuthorities().iterator().next().getAuthority()
        ));
    }
}
