package com.example.predios.dto;

import com.example.predios.enums.EstadoPredio;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredioDTO {

    private Long id;

    @NotNull(message = "El idProductor es requerido")
    private Long idProductor;

    @NotBlank(message = "El nombre del predio no puede estar vacío")
    private String nombre;

    @NotNull(message = "El idMunicipio es requerido")
    private Long idMunicipio;

    @Positive(message = "El área debe ser positiva")
    private Double areaHectareas;

    private Double latitud;

    private Double longitud;

    private EstadoPredio estado;
}
