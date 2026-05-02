package com.example.inspecciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PLAGAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plaga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plaga_seq")
    @SequenceGenerator(name = "plaga_seq", sequenceName = "SEQ_PLAGAS", allocationSize = 1)
    @Column(name = "ID_PLAGA")
    private Long id;

    @Column(name = "ID_INSPECCION", nullable = false)
    private Long idInspeccion;

    @Column(name = "NOMBRE_PLAGA", nullable = false, length = 100)
    private String nombrePlaga;

    @Column(name = "TIPO_PLAGA", length = 50)
    private String tipoPlaga; // INSECTO, HONGO, BACTERIA, VIRUS, MALEZA

    @Column(name = "SEVERIDAD", length = 50)
    private String severidad; // BAJA, MEDIA, ALTA, CRITICA

    @Column(name = "PORCENTAJE_AFECTACION")
    private Double porcentajeAfectacion;

}
