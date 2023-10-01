package com.codigo.semana7.proveedor.application.usecase;

import com.codigo.semana7.proveedor.domain.model.Proveedor;
import com.codigo.semana7.proveedor.domain.ports.in.ProveedorUseCase;
import com.codigo.semana7.proveedor.domain.ports.out.ProveedorRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProveedorUseCaseImpl implements ProveedorUseCase {
    private final ProveedorRepositoryPort proveedorRepositoryPort;

    public ProveedorUseCaseImpl(ProveedorRepositoryPort proveedorRepositoryPort) {
        this.proveedorRepositoryPort = proveedorRepositoryPort;
    }

    @Override
    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorRepositoryPort.save(proveedor);
    }

    @Override
    public List<Proveedor> getProveedores() {
        return proveedorRepositoryPort.findAll();
    }

    @Override
    public Optional<Proveedor> getProveedor(Long id) {
        return proveedorRepositoryPort.findById(id);
    }

    @Override
    public Optional<Proveedor> updateProveedor(Proveedor proveedor) {
        return proveedorRepositoryPort.update(proveedor);
    }

    @Override
    public Optional<Proveedor> deleteProveedor(Long id) {
        return proveedorRepositoryPort.deleteById(id);
    }
}
