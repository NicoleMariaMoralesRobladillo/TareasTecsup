package com.codigo.semana7.categoria.application.service;

import com.codigo.semana7.categoria.domain.model.Categoria;
import com.codigo.semana7.categoria.domain.ports.in.CategoriaUseCase;

import java.util.List;
import java.util.Optional;

public class CategoriaService implements CategoriaUseCase {
    private final CategoriaUseCase categoriaUseCase;

    public CategoriaService(CategoriaUseCase categoriaUseCase) {
        this.categoriaUseCase = categoriaUseCase;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaUseCase.crearCategoria(categoria);
    }

    @Override
    public List<Categoria> getCategorias() {
        return categoriaUseCase.getCategorias();
    }

    @Override
    public Optional<Categoria> getCategoria(Long id) {
        return categoriaUseCase.getCategoria(id);
    }

    @Override
    public Optional<Categoria> updateCategoria(Categoria categoria) {
        return categoriaUseCase.updateCategoria(categoria);
    }

    @Override
    public Optional<Categoria> deleteCategoria(Long id) {
        return categoriaUseCase.deleteCategoria(id);
    }
}
