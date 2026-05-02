package com.example.inspecciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_RESULTADOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultado_seq")
    @SequenceGenerator(name = "resultado_seq", sequenceName = "SEQ_RESULTADOS", allocationSize = 1)
    @Column(name = "ID_RESULTADO")
    private Long id;

    @Column(name = "ID_INSPECCION", nullable = false)
    private Long idInspeccion;

    @Column(name = "RECOMENDACION", length = 1000)
    private String recomendacion;

    @Column(name = "TRATAMIENTO_SUGERIDO", length = 500)
    private String tratamientoSugerido;

    @Column(name = "FECHA_SEGUIMIENTO")
    private java.util.Date fechaSeguimiento;

    @Column(name = "ESTADO", length = 20)
    private String estado; // PENDIENTE, EN_PROCESO, RESUELTO

}
