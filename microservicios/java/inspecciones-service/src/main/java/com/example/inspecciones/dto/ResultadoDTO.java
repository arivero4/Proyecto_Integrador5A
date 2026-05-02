package com.example.inspecciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDTO {

    private Long id;

    private Long idInspeccion;

    private String recomendacion;

    private String tratamientoSugerido;

    private java.util.Date fechaSeguimiento;

    private String estado;

}
