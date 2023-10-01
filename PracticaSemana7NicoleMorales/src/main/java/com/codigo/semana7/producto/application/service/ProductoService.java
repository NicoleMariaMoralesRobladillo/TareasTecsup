package com.codigo.semana7.producto.application.service;

import com.codigo.semana7.producto.domain.model.Producto;
import com.codigo.semana7.producto.domain.ports.in.ProductoUseCase;

import java.util.List;
import java.util.Optional;

public class ProductoService implements ProductoUseCase {
    private final ProductoUseCase productoUseCase;

    public ProductoService(ProductoUseCase productoUseCase) {
        this.productoUseCase = productoUseCase;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoUseCase.crearProducto(producto);
    }

    @Override
    public List<Producto> getProductos() {
        return productoUseCase.getProductos();
    }

    @Override
    public Optional<Producto> getProducto(Long id) {
        return productoUseCase.getProducto(id);
    }

    @Override
    public Optional<Producto> updateProducto(Producto producto) {
        return productoUseCase.updateProducto(producto);
    }

    @Override
    public Optional<Producto> deleteProducto(Long id) {
        return productoUseCase.deleteProducto(id);
    }
}
