package com.example.inspecciones.dto;

import com.example.inspecciones.enums.EstadoInspeccion;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspeccionFitosanitariaDTO {

    private Long id;

    @NotNull(message = "El idPredio es requerido")
    private Long idPredio;

    @NotNull(message = "El idAsistente es requerido")
    private Long idAsistente;

    @NotNull(message = "La fecha de inspección es requerida")
    private java.util.Date fechaInspeccion;

    private String observaciones;

    private EstadoInspeccion estado;
}
