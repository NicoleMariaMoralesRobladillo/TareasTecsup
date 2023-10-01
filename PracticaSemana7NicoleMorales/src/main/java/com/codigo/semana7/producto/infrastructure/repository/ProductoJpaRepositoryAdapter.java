package com.codigo.semana7.producto.infrastructure.repository;

import com.codigo.semana7.producto.domain.model.Producto;
import com.codigo.semana7.producto.domain.ports.out.ProductoRepositoryPort;
import com.codigo.semana7.producto.infrastructure.entity.ProductoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductoJpaRepositoryAdapter implements ProductoRepositoryPort {
    private final ProductoJpaRepository productoJpaRepository;

    public ProductoJpaRepositoryAdapter(ProductoJpaRepository productoJpaRepository) {
        this.productoJpaRepository = productoJpaRepository;
    }

    @Override
    public Producto save(Producto producto) {
        ProductoEntity productoEntity = ProductoEntity.fromDomainModel(producto);
        ProductoEntity saveProductoEntity = productoJpaRepository.save(productoEntity);
        return saveProductoEntity.toDomainModel();
    }

    @Override
    public List<Producto> findAll() {
        return productoJpaRepository.findAll()
                .stream().map(ProductoEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoJpaRepository.findById(id).map(ProductoEntity::toDomainModel);
    }

    @Override
    public Optional<Producto> update(Producto producto) {
        if (productoJpaRepository.existsById(producto.getId())) {
            ProductoEntity productoEntity = ProductoEntity.fromDomainModel(producto);
            ProductoEntity updateProductoEntity = productoJpaRepository.save(productoEntity);
            return Optional.of(updateProductoEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Producto> deleteById(Long id) {
        if (productoJpaRepository.existsById(id)) {
            Optional<Producto> deleteProducto = productoJpaRepository.findById(id).map(ProductoEntity::toDomainModel);
            productoJpaRepository.deleteById(id);
            return deleteProducto;
        }
        return Optional.empty();
    }
}