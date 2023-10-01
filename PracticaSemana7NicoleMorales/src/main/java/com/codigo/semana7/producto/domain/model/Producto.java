package com.codigo.semana7.producto.domain.model;

import com.codigo.semana7.categoria.domain.model.Categoria;
import com.codigo.semana7.proveedor.domain.model.Proveedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private Long id;

    @Size(max = 255, message = "El nombre del producto debe tener máximo 255 caracteres.")
    @NotBlank(message = "El nombre del producto no puede estar en blanco.")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio del producto no puede ser nulo.")
    private Double precio;

    private Integer estado;

    private Categoria categoria;

    private Proveedor proveedor;
}