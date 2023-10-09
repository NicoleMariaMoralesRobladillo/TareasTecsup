package com.codigo.semana8.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direccion_id")
    private Long id;

    @NotBlank(message = "La calle de la dirección no puede estar en blanco.")
    @Column(nullable = false)
    private String calle;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
}