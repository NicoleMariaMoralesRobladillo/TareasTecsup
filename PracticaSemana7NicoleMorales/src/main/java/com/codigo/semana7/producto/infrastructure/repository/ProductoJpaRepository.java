package com.codigo.semana7.producto.infrastructure.repository;

import com.codigo.semana7.producto.infrastructure.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {}
