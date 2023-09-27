package com.codigo.semana7.persona.domain.ports.in;

import com.codigo.semana7.persona.domain.model.Persona;

import java.util.Optional;

public interface PersonaUseCase {
    Persona crearPersona(Persona persona);
    Optional<Persona> getPersona(Long id);
    Optional<Persona> updatePersona(Persona persona);
    Optional<Persona> deletePersona(Long id);
}
