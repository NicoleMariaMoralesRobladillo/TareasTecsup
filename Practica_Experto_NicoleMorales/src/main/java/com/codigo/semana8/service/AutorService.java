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
            nombre = autor.getNombre();
        }
        autorEncontrado = autorRepository.findByNombre(nombre);
        if (autorEncontrado.isPresent()) {
            return autorEncontrado.get();
        }
        else {
            throw new RuntimeException("Autor no encontrado.");
        }
    }

    public String crearAutor(Autor autor) {
        Optional<Autor> autorEncontrado = autorRepository.findByNombre(autor.getNombre());
        Autor autorGuardado;
        if (autorEncontrado.isEmpty()) {
            autor.setId(null);
            autorGuardado = autorRepository.save(autor);
            return "El autor con id " + autorGuardado.getId() + " se ha creado correctamente.";
        }
        else {
            throw new RuntimeException("El autor ya existe.");
        }
    }

    public String actualizarAutor(Long id, Autor autor) {
        Autor autorExistente = obtenerAutorPorId(id);
        autorExistente.setNombre(autor.getNombre());
        autorRepository.save(autorExistente);
        return "El autor con id " + autorExistente.getId() + "se ha actualizado correctamente.";
    }

    public String eliminarAutor(Long id) {
        Autor autorExistente = obtenerAutorPorId(id);
        autorRepository.delete(autorExistente);
        return "El autor con id " + autorExistente.getId() + " se ha eliminado correctamente.";
    }
}
