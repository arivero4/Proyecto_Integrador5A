package com.example.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductorDTO {

    private Long id;

    private Long idUsuario;

    @NotBlank(message = "La cédula no puede estar vacía")
    private String cedula;

    private String razonSocial;

    @Positive(message = "El número de hectáreas debe ser positivo")
    private Double numeroHectareas;

    private String certificaciones;

}
