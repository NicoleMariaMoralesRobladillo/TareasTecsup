package com.codigo.semana7.producto.application.usecase;

import com.codigo.semana7.producto.domain.model.Producto;
import com.codigo.semana7.producto.domain.ports.in.ProductoUseCase;
import com.codigo.semana7.producto.domain.ports.out.ProductoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProductoUseCaseImpl implements ProductoUseCase {
    private final ProductoRepositoryPort productoRepositoryPort;

    public ProductoUseCaseImpl(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepositoryPort.save(producto);
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepositoryPort.findAll();
    }

    @Override
    public Optional<Producto> getProducto(Long id) {
        return productoRepositoryPort.findById(id);
    }

    @Override
    public Optional<Producto> updateProducto(Producto producto) {
        return productoRepositoryPort.update(producto);
    }

    @Override
    public Optional<Producto> deleteProducto(Long id) {
        return productoRepositoryPort.deleteById(id);
    }
}
