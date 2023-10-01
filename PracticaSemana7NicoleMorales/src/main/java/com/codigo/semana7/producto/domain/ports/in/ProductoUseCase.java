package com.codigo.semana7.producto.domain.ports.in;

import com.codigo.semana7.producto.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoUseCase {
    Producto crearProducto(Producto producto);
    List<Producto> getProductos();
    Optional<Producto> getProducto(Long id);
    Optional<Producto> updateProducto(Producto producto);
    Optional<Producto> deleteProducto(Long id);
}
