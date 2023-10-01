package com.codigo.semana7.proveedor.application.service;

import com.codigo.semana7.proveedor.domain.model.Proveedor;
import com.codigo.semana7.proveedor.domain.ports.in.ProveedorUseCase;

import java.util.List;
import java.util.Optional;

public class ProveedorService implements ProveedorUseCase {
    private final ProveedorUseCase proveedorUseCase;

    public ProveedorService(ProveedorUseCase proveedorUseCase) {
        this.proveedorUseCase = proveedorUseCase;
    }

    @Override
    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorUseCase.crearProveedor(proveedor);
    }

    @Override
    public List<Proveedor> getProveedores() {
        return proveedorUseCase.getProveedores();
    }

    @Override
    public Optional<Proveedor> getProveedor(Long id) {
        return proveedorUseCase.getProveedor(id);
    }

    @Override
    public Optional<Proveedor> updateProveedor(Proveedor proveedor) {
        return proveedorUseCase.updateProveedor(proveedor);
    }

    @Override
    public Optional<Proveedor> deleteProveedor(Long id) {
        return proveedorUseCase.deleteProveedor(id);
    }
}
