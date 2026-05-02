package com.example.inspecciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlagaDTO {

    private Long id;

    private Long idInspeccion;

    @NotBlank(message = "El nombre de la plaga no puede estar vacío")
    private String nombrePlaga;

    private String tipoPlaga;

    private String severidad;

    @Positive(message = "El porcentaje de afectación debe ser positivo")
    private Double porcentajeAfectacion;

}
