package com.example.predios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_MUNICIPIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_seq")
    @SequenceGenerator(name = "municipio_seq", sequenceName = "SEQ_MUNICIPIOS", allocationSize = 1)
    @Column(name = "ID_MUNICIPIO")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "DEPARTAMENTO", length = 100)
    private String departamento;

    @Column(name = "CODIGO_DANE", length = 10)
    private String codigoDane;

}
