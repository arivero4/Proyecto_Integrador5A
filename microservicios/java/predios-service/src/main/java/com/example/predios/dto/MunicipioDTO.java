package com.example.predios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MunicipioDTO {

    private Long id;

    @NotBlank(message = "El nombre del municipio no puede estar vacío")
    private String nombre;

    private String departamento;

    private String codigoDane;

}
