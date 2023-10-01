package com.codigo.semana8.service;

import com.codigo.semana8.model.Libro;
import com.codigo.semana8.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> obtenerTodosLibros() {
        return libroRepository.findAll();
    }

    public Libro obtenerLibroPorId(Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            return libro.get();
        }
        else {
            throw new RuntimeException("Libro no encontrado.");
        }
    }

    public Libro crearLibro(Libro libro) {
        libro.setId(null);
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(Long id, Libro libro) {
        Libro libroExistente = obtenerLibroPorId(id);
        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setEstado(libro.getEstado());
        libroExistente.setEditor(libro.getEditor());
        libroExistente.setAutor(libro.getAutor());
        return libroRepository.save(libroExistente);
    }

    public Libro eliminarLibroLogicamente(Long id){
        Libro libroExistente = obtenerLibroPorId(id);
        libroExistente.setEstado(0);
        return libroRepository.save(libroExistente);
    }
}