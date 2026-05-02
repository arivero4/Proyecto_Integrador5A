package com.example.predios.entity;

import com.example.predios.enums.EstadoPredio;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "El idProductor es obligatorio")
    @Column(name = "ID_PRODUCTOR", nullable = false)
    private Long idProductor;

    @NotBlank(message = "El nombre del predio es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El idMunicipio es obligatorio")
    @Column(name = "ID_MUNICIPIO", nullable = false)
    private Long idMunicipio;

    @NotNull(message = "El área en hectáreas es obligatoria")
    @Positive(message = "El área debe ser un valor positivo")
    @Column(name = "AREA_HECTAREAS", nullable = false)
    private Double areaHectareas;

    @DecimalMin(value = "-90.0", message = "La latitud debe estar entre -90 y 90")
    @DecimalMax(value = "90.0", message = "La latitud debe estar entre -90 y 90")
    @Column(name = "LATITUD")
    private Double latitud;

    @DecimalMin(value = "-180.0", message = "La longitud debe estar entre -180 y 180")
    @DecimalMax(value = "180.0", message = "La longitud debe estar entre -180 y 180")
    @Column(name = "LONGITUD")
    private Double longitud;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20)
    private EstadoPredio estado;
}
