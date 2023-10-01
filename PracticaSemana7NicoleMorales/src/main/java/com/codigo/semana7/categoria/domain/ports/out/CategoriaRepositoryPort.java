package com.codigo.semana7.categoria.domain.ports.out;

import com.codigo.semana7.categoria.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {
    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    Optional<Categoria> update(Categoria categoria);
    Optional<Categoria> deleteById(Long id);
}
