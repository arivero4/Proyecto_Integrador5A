package com.example.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenteTecnicoDTO {

    private Long id;

    private Long idUsuario;

    @NotBlank(message = "La cédula no puede estar vacía")
    private String cedula;

    private String especialidad;

    private String numeroLicencia;

    private String estadoLicencia;

}
