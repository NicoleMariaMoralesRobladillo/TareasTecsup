package com.codigo.semana7.categoria.domain.ports.in;

import com.codigo.semana7.categoria.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaUseCase {
    Categoria crearCategoria(Categoria categoria);
    List<Categoria> getCategorias();
    Optional<Categoria> getCategoria(Long id);
    Optional<Categoria> updateCategoria(Categoria categoria);
    Optional<Categoria> deleteCategoria(Long id);
}
