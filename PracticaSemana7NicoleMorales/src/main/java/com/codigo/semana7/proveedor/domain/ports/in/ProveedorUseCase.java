package com.codigo.semana7.proveedor.domain.ports.in;

import com.codigo.semana7.proveedor.domain.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorUseCase {
    Proveedor crearProveedor(Proveedor proveedor);
    List<Proveedor> getProveedores();
    Optional<Proveedor> getProveedor(Long id);
    Optional<Proveedor> updateProveedor(Proveedor proveedor);
    Optional<Proveedor> deleteProveedor(Long id);
}
