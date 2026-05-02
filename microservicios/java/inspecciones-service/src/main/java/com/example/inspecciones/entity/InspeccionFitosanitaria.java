package com.example.inspecciones.entity;

import com.example.inspecciones.enums.EstadoInspeccion;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @NotNull(message = "El idPredio es obligatorio")
    @Column(name = "ID_PREDIO", nullable = false)
    private Long idPredio;

    @NotNull(message = "El idAsistente es obligatorio")
    @Column(name = "ID_ASISTENTE", nullable = false)
    private Long idAsistente;

    @NotNull(message = "La fecha de inspección es obligatoria")
    @Column(name = "FECHA_INSPECCION", nullable = false)
    private Date fechaInspeccion;

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    @Column(name = "OBSERVACIONES", length = 1000)
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20)
    private EstadoInspeccion estado;
}
