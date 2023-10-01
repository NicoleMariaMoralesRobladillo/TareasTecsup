package com.codigo.semana7.producto.domain.ports.out;

import com.codigo.semana7.producto.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Optional<Producto> update(Producto producto);
    Optional<Producto> deleteById(Long id);
}
