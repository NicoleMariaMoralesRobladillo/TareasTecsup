package com.codigo.semana8.service;

import com.codigo.semana8.model.Persona;
import com.codigo.semana8.repository.PersonaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> obtenerTodasPersonas() {
        return personaRepository.findAll();
    }

    public Persona obtenerPersonaPorId(Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isPresent()) {
            return persona.get();
        }
        else {
            throw new RuntimeException("Persona no encontrada.");
        }
    }

    public Persona obtenerPersonaPorNombre(String nombre) throws JsonProcessingException {
        Optional<Persona> personaEncontrada;
        if (nombre.contains("{")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Persona persona = objectMapper.readValue(nombre, Persona.class);
            nombre = persona.getNombre();
        }
        personaEncontrada = personaRepository.findByNombre(nombre);
        if (personaEncontrada.isPresent()) {
            return personaEncontrada.get();
        }
        else {
            throw new RuntimeException("Persona no encontrada.");
        }
    }

    public String crearPersona(Persona persona) {
        Optional<Persona> personaEncontrada = personaRepository.findByNombre(persona.getNombre());
        Persona personaGuardada;
        if (personaEncontrada.isEmpty()) {
            persona.setId(null);
            personaGuardada = personaRepository.save(persona);
            return "La persona con id " + personaGuardada.getId() + " se ha creado correctamente.";
        }
        else {
            throw new RuntimeException("La persona ya existe.");
        }
    }

    public String actualizarPersona(Long id, Persona persona) {
        Persona personaExistente = obtenerPersonaPorId(id);
        personaExistente.setNombre(persona.getNombre());
        personaRepository.save(personaExistente);
        return "La persona con id " + personaExistente.getId() + "se ha actualizado correctamente.";
    }

    public String eliminarPersona(Long id) {
        Persona personaExistente = obtenerPersonaPorId(id);
        personaRepository.delete(personaExistente);
        return "La persona con id " + personaExistente.getId() + " se ha eliminado correctamente.";
    }
}