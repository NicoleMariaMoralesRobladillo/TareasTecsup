package com.codigo.semana7.proveedor.domain.ports.out;

import com.codigo.semana7.proveedor.domain.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepositoryPort {
    Proveedor save(Proveedor proveedor);
    List<Proveedor> findAll();
    Optional<Proveedor> findById(Long id);
    Optional<Proveedor> update(Proveedor proveedor);
    Optional<Proveedor> deleteById(Long id);
}
