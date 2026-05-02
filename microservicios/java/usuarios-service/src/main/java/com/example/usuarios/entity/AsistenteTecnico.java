package com.example.usuarios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_ASISTENTES_TECNICOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenteTecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asistente_seq")
    @SequenceGenerator(name = "asistente_seq", sequenceName = "SEQ_ASISTENTES", allocationSize = 1)
    @Column(name = "ID_ASISTENTE")
    private Long id;

    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;

    @Column(name = "CEDULA", nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(name = "ESPECIALIDAD", length = 100)
    private String especialidad;

    @Column(name = "NUMERO_LICENCIA", unique = true, length = 50)
    private String numeroLicencia;

    @Column(name = "ESTADO_LICENCIA", length = 20)
    private String estadoLicencia; // VIGENTE, VENCIDA, SUSPENDIDA

}
