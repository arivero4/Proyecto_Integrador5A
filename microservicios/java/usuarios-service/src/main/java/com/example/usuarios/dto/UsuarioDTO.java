package com.example.usuarios.dto;

import com.example.usuarios.enums.EstadoUsuario;
import com.example.usuarios.enums.TipoUsuario;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @Pattern(regexp = "^[0-9\\-\\+\\(\\)\\s]{7,15}$", message = "El teléfono no es válido")
    private String telefono;

    @NotNull(message = "El tipo de usuario es requerido. Valores: PRODUCTOR, ASISTENTE_TECNICO, ADMIN")
    private TipoUsuario tipoUsuario;

    private EstadoUsuario estado;
}
