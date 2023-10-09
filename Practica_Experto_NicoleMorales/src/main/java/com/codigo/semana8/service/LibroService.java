package com.codigo.semana8.service;

import com.codigo.semana8.model.Libro;
import com.codigo.semana8.repository.LibroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public Libro obtenerLibroPorTitulo(String titulo) throws JsonProcessingException {
        Optional<Libro> libroEncontrado;
        if (titulo.contains("{")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Libro libro = objectMapper.readValue(titulo, Libro.class);
            titulo = libro.getTitulo();
        }
        libroEncontrado = libroRepository.findByTitulo(titulo);
        if (libroEncontrado.isPresent()) {
            return libroEncontrado.get();
        }
        else {
            throw new RuntimeException("Libro no encontrado.");
        }
    }

    public String crearLibro(Libro libro) {
        Optional<Libro> libroEncontrado = libroRepository.findByTitulo(libro.getTitulo());
        Libro libroGuardado;
        if (libroEncontrado.isEmpty()) {
            libro.setId(null);
            libroGuardado = libroRepository.save(libro);
            return "El libro con id " + libroGuardado.getId() + " se ha creado correctamente.";
        }
        else {
            throw new RuntimeException("El libro ya existe.");
        }
    }

    public String actualizarLibro(Long id, Libro libro) {
        Libro libroExistente = obtenerLibroPorId(id);
        libroExistente.setTitulo(libro.getTitulo());
        libroRepository.save(libroExistente);
        return "El libro con id " + libroExistente.getId() + "se ha actualizado correctamente.";
    }

    public String eliminarLibro(Long id) {
        Libro libroExistente = obtenerLibroPorId(id);
        libroRepository.delete(libroExistente);
        return "El libro con id " + libroExistente.getId() + " se ha eliminado correctamente.";
    }
}