package com.example.predios.entity;

import com.example.predios.enums.EstadoPredio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PREDIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Predio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "predio_seq")
    @SequenceGenerator(name = "predio_seq", sequenceName = "SEQ_PREDIOS", allocationSize = 1)
    @Column(name = "ID_PREDIO")
    private Long id;

    @Column(name = "ID_PRODUCTOR", nullable = false)
    private Long idProductor;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ID_MUNICIPIO", nullable = false)
    private Long idMunicipio;

    @Column(name = "AREA_HECTAREAS", nullable = false)
    private Double areaHectareas;

    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20)
    private EstadoPredio estado;
}
