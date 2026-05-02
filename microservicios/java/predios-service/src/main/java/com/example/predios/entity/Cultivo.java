package com.example.predios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CULTIVOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cultivo_seq")
    @SequenceGenerator(name = "cultivo_seq", sequenceName = "SEQ_CULTIVOS", allocationSize = 1)
    @Column(name = "ID_CULTIVO")
    private Long id;

    @Column(name = "ID_PREDIO", nullable = false)
    private Long idPredio;

    @Column(name = "TIPO_CULTIVO", nullable = false, length = 100)
    private String tipoCultivo; // CAFE, ARROZ, PAPA, MAIZ, etc

    @Column(name = "AREA_HECTAREAS", nullable = false)
    private Double areaHectareas;

    @Column(name = "FECHA_SIEMBRA")
    private java.util.Date fechaSiembra;

    @Column(name = "FECHA_COSECHA_ESTIMADA")
    private java.util.Date fechaCosechaEstimada;

    @Column(name = "ESTADO", length = 20)
    private String estado; // SIEMBRA, CRECIMIENTO, COSECHA, RECOLECCION

}
