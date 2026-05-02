package com.example.inspecciones.entity;

import com.example.inspecciones.enums.EstadoInspeccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_INSPECCIONES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspeccionFitosanitaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inspeccion_seq")
    @SequenceGenerator(name = "inspeccion_seq", sequenceName = "SEQ_INSPECCIONES", allocationSize = 1)
    @Column(name = "ID_INSPECCION")
    private Long id;

    @Column(name = "ID_PREDIO", nullable = false)
    private Long idPredio;

    @Column(name = "ID_ASISTENTE", nullable = false)
    private Long idAsistente;

    @Column(name = "FECHA_INSPECCION", nullable = false)
    private java.util.Date fechaInspeccion;

    @Column(name = "OBSERVACIONES", length = 1000)
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20)
    private EstadoInspeccion estado;
}
