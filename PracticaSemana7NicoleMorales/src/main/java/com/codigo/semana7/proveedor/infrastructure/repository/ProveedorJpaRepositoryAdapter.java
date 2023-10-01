package com.codigo.semana7.proveedor.infrastructure.repository;

import com.codigo.semana7.proveedor.domain.model.Proveedor;
import com.codigo.semana7.proveedor.domain.ports.out.ProveedorRepositoryPort;
import com.codigo.semana7.proveedor.infrastructure.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProveedorJpaRepositoryAdapter implements ProveedorRepositoryPort {
    private final ProveedorJpaRepository proveedorJpaRepository;

    public ProveedorJpaRepositoryAdapter(ProveedorJpaRepository proveedorJpaRepository) {
        this.proveedorJpaRepository = proveedorJpaRepository;
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        ProveedorEntity proveedorEntity = ProveedorEntity.fromDomainModel(proveedor);
        ProveedorEntity saveProveedorEntity = proveedorJpaRepository.save(proveedorEntity);
        return saveProveedorEntity.toDomainModel();
    }

    @Override
    public List<Proveedor> findAll() {
        return proveedorJpaRepository.findAll()
                .stream().map(ProveedorEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Proveedor> findById(Long id) {
        return proveedorJpaRepository.findById(id).map(ProveedorEntity::toDomainModel);
    }

    @Override
    public Optional<Proveedor> update(Proveedor proveedor) {
        if (proveedorJpaRepository.existsById(proveedor.getId())) {
            ProveedorEntity proveedorEntity = ProveedorEntity.fromDomainModel(proveedor);
            ProveedorEntity updateProveedorEntity = proveedorJpaRepository.save(proveedorEntity);
            return Optional.of(updateProveedorEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Proveedor> deleteById(Long id) {
        if (proveedorJpaRepository.existsById(id)) {
            Optional<Proveedor> deleteProveedor = proveedorJpaRepository.findById(id).map(ProveedorEntity::toDomainModel);
            proveedorJpaRepository.deleteById(id);
            return deleteProveedor;
        }
        return Optional.empty();
    }
}