package com.codigo.semana8.repository;

import com.codigo.semana8.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    Optional<Direccion> findByCalle(String calle);
}