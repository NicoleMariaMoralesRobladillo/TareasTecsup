package com.codigo.semana7.categoria.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    private Long id;

    @Size(max = 255, message = "El nombre de la categoría debe tener máximo 255 caracteres.")
    @NotBlank(message = "El nombre de la categoría no puede estar en blanco.")
    private String nombre;

    private Integer estado;
}