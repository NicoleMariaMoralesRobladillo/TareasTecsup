package com.codigo.semana8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autor_id")
    private Long id;

    @NotBlank(message = "El nombre del autor no puede estar en blanco.")
    @Column(nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnore
    private List<Libro> libros = new ArrayList<>();
}