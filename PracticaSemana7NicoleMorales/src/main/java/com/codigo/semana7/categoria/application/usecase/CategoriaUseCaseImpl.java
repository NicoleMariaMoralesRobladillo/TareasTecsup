package com.codigo.semana7.categoria.application.usecase;

import com.codigo.semana7.categoria.domain.model.Categoria;
import com.codigo.semana7.categoria.domain.ports.in.CategoriaUseCase;
import com.codigo.semana7.categoria.domain.ports.out.CategoriaRepositoryPort;

import java.util.List;
import java.util.Optional;

public class CategoriaUseCaseImpl implements CategoriaUseCase {
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public CategoriaUseCaseImpl(CategoriaRepositoryPort categoriaRepositoryPort) {
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepositoryPort.save(categoria);
    }

    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepositoryPort.findAll();
    }

    @Override
    public Optional<Categoria> getCategoria(Long id) {
        return categoriaRepositoryPort.findById(id);
    }

    @Override
    public Optional<Categoria> updateCategoria(Categoria categoria) {
        return categoriaRepositoryPort.update(categoria);
    }

    @Override
    public Optional<Categoria> deleteCategoria(Long id) {
        return categoriaRepositoryPort.deleteById(id);
    }
}
