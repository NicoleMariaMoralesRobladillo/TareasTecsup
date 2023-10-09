package com.codigo.semana8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long id;

    @NotBlank(message = "El nombre de la persona no puede estar en blanco.")
    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "persona")
    @JsonIgnore
    private List<Direccion> direcciones = new ArrayList<>();
}