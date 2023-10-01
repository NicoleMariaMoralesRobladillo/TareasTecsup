package com.codigo.semana7.proveedor.infrastructure.repository;

import com.codigo.semana7.proveedor.infrastructure.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorJpaRepository extends JpaRepository<ProveedorEntity, Long> {}
