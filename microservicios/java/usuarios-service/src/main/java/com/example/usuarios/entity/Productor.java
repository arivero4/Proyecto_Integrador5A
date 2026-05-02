package com.example.usuarios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PRODUCTORES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Productor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productor_seq")
    @SequenceGenerator(name = "productor_seq", sequenceName = "SEQ_PRODUCTORES", allocationSize = 1)
    @Column(name = "ID_PRODUCTOR")
    private Long id;

    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;

    @Column(name = "CEDULA", nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(name = "RAZON_SOCIAL", length = 150)
    private String razonSocial;

    @Column(name = "NUMERO_HECTAREAS")
    private Double numeroHectareas;

    @Column(name = "CERTIFICACIONES", length = 255)
    private String certificaciones;

}
