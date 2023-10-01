package com.codigo.semana8.service;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerTodosCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return categoria.get();
        }
        else {
            throw new RuntimeException("Categoria no encontrado.");
        }
    }

    public Categoria crearCategoria(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaExistente = obtenerCategoriaPorId(id);
        categoriaExistente.setNombre(categoria.getNombre());
        categoriaExistente.setEstado(categoria.getEstado());
        return categoriaRepository.save(categoriaExistente);
    }

    public Categoria eliminarCategoriaLogicamente(Long id){
        Categoria categoriaExistente = obtenerCategoriaPorId(id);
        categoriaExistente.setEstado(0);
        return categoriaRepository.save(categoriaExistente);
    }
}