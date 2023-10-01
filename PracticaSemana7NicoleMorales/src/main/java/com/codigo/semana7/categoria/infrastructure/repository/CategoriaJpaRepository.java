package com.codigo.semana7.categoria.infrastructure.repository;

import com.codigo.semana7.categoria.infrastructure.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, Long> {}
