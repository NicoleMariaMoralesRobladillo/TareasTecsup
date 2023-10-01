package com.codigo.semana7.proveedor.domain.model;

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
public class Proveedor {
    private Long id;

    @Size(max = 255, message = "El nombre del proveedor debe tener máximo 255 caracteres.")
    @NotBlank(message = "El nombre del proveedor no puede estar en blanco.")
    private String nombre;

    @Size(max = 255, message = "La dirección del proveedor debe tener máximo 255 caracteres.")
    private String direccion;

    @Size(max = 15, message = "El teléfono del proveedor debe tener máximo 255 caracteres.")
    private String telefono;

    private Integer estado;
}