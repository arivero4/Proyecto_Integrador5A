package com.example.usuarios.dto;

import com.example.usuarios.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {

    @NotBlank(message = "El email es requerido")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    private String password;

    // Solo para registro
    @Size(min = 3, max = 100)
    private String nombre;

    private String telefono;

    private TipoUsuario tipoUsuario;
}
