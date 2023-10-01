package com.codigo.semana7.categoria.infrastructure.repository;

import com.codigo.semana7.categoria.domain.model.Categoria;
import com.codigo.semana7.categoria.domain.ports.out.CategoriaRepositoryPort;
import com.codigo.semana7.categoria.infrastructure.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoriaJpaRepositoryAdapter implements CategoriaRepositoryPort {
    private final CategoriaJpaRepository categoriaJpaRepository;

    public CategoriaJpaRepositoryAdapter(CategoriaJpaRepository categoriaJpaRepository) {
        this.categoriaJpaRepository = categoriaJpaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity categoriaEntity = CategoriaEntity.fromDomainModel(categoria);
        CategoriaEntity saveCategoriaEntity = categoriaJpaRepository.save(categoriaEntity);
        return saveCategoriaEntity.toDomainModel();
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaJpaRepository.findAll()
                .stream().map(CategoriaEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaJpaRepository.findById(id).map(CategoriaEntity::toDomainModel);
    }

    @Override
    public Optional<Categoria> update(Categoria categoria) {
        if (categoriaJpaRepository.existsById(categoria.getId())) {
            CategoriaEntity categoriaEntity = CategoriaEntity.fromDomainModel(categoria);
            CategoriaEntity updateCategoriaEntity = categoriaJpaRepository.save(categoriaEntity);
            return Optional.of(updateCategoriaEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Categoria> deleteById(Long id) {
        if (categoriaJpaRepository.existsById(id)) {
            Optional<Categoria> deleteCategoria = categoriaJpaRepository.findById(id).map(CategoriaEntity::toDomainModel);
            categoriaJpaRepository.deleteById(id);
            return deleteCategoria;
        }
        return Optional.empty();
    }
}