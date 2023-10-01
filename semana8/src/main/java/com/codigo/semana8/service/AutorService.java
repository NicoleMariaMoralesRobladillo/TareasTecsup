package com.codigo.semana8.service;

import com.codigo.semana8.model.Autor;
import com.codigo.semana8.repository.AutorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> obtenerTodosAutores() {
        return autorRepository.findAll();
    }

    public Autor obtenerAutorPorId(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            return autor.get();
        }
        else {
            throw new RuntimeException("Autor no encontrado.");
        }
    }

    public Autor obtenerAutorPorNombre(String nombre) throws JsonProcessingException {
        Optional<Autor> autorEncontrado;
        if (nombre.contains("{")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Autor autor = objectMapper.readValue(nombre, Autor.class);
            autorEncontrado = autorRepository.findByNombre(autor.getNombre());
        }
        else {
            autorEncontrado = autorRepository.findByNombre(nombre);
        }
        if (autorEncontrado.isPresent()) {
            return autorEncontrado.get();
        }
        else {
            throw new RuntimeException("Autor no encontrado.");
        }
    }

    public Autor crearAutor(Autor autor) {
        Optional<Autor> autorEncontrado = autorRepository.findByNombre(autor.getNombre());
        if (autorEncontrado.isEmpty()) {
            autor.setId(null);
            return autorRepository.save(autor);
        }
        else {
            throw new RuntimeException("Autor ya existe.");
        }
    }

    public Autor actualizarAutor(Long id, Autor autor) {
        Autor autorExistente = obtenerAutorPorId(id);
        autorExistente.setNombre(autor.getNombre());
        autorExistente.setEstado(autor.getEstado());
        return autorRepository.save(autorExistente);
    }

    public Autor eliminarAutorLogicamente(Long id){
        Autor autorExistente = obtenerAutorPorId(id);
        autorExistente.setEstado(0);
        return autorRepository.save(autorExistente);
    }
}