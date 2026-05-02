package com.example.predios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CultivoDTO {

    private Long id;

    private Long idPredio;

    @NotBlank(message = "El tipo de cultivo no puede estar vacío")
    private String tipoCultivo;

    @Positive(message = "El área debe ser positiva")
    private Double areaHectareas;

    private java.util.Date fechaSiembra;

    private java.util.Date fechaCosechaEstimada;

    private String estado;

}
