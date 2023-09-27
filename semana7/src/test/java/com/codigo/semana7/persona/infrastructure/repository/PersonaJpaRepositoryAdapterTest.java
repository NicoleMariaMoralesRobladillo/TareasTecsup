package com.codigo.semana7.persona.infrastructure.repository;

import com.codigo.semana7.persona.domain.model.Persona;
import com.codigo.semana7.persona.infrastructure.entity.PersonaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class PersonaJpaRepositoryAdapterTest {
    @Mock
    PersonaJpaRepository personaJpaRepository;

    @InjectMocks
    PersonaJpaRepositoryAdapter personaJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personaJpaRepositoryAdapter = new PersonaJpaRepositoryAdapter(personaJpaRepository);
    }

    @Test
    void savePersonaEntityExitoso() {
        // Lo que enviamos al método de la clase que estamos probando
        Persona persona = new Persona(
                1L,
                "Paul",
                "Rodriguez",
                new Date(),
                "Masculino");

        // Lo que enviamos a BD como simulación
        PersonaEntity personaEntity = new PersonaEntity(
                1L,
                "Paul",
                "Rodriguez",
                new Date(),
                "Masculino");

        // Simulando comportamiento
        when(personaJpaRepository.save(
                Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);

        Persona personaAdapter = personaJpaRepositoryAdapter.save(persona);

        assertNotNull(personaAdapter);
        assertEquals(persona.getId(), personaAdapter.getId());
        assertEquals(persona.getNombre(), personaAdapter.getNombre());
    }

    @Test
    void findByIdPersonaEntityExitoso() {
        PersonaEntity personaEntity = new PersonaEntity(
                1L,
                "Paul",
                "Rodriguez",
                new Date(),
                "Masculino");
        Long id = 1L;
        when(personaJpaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(personaEntity));
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.findById(id);
        assertNotNull(personaAdapter);
        personaAdapter.map(persona -> {
            Long personaId = persona.getId();
            assertEquals(personaId, id);
            return personaId;
        });
    }

    @Test
    void findByIdPersonaEntityNotFound() {
        Long id = 1L;
        when(personaJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.findById(id);
        assertTrue(personaAdapter.isEmpty());
    }

    @Test
    void updatePersonaEntityExitoso() {
        Persona persona = new Persona(
                1L,
                "Paul",
                "Rodriguez",
                new Date(),
                "Masculino");
        PersonaEntity personaEntity = new PersonaEntity(
                1L,
                "Paul",
                "Rodriguez",
                new Date(),
                "Masculino");
        when(personaJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(personaJpaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.update(persona);
        assertNotNull(personaAdapter);
        personaAdapter.map(
                personaActual -> {
                    assertEquals(persona.getId(), personaActual.getId());
                    assertEquals(persona.getNombre(), personaActual.getNombre());
                    return personaActual;
                }
        );
    }

    @Test
    void updatePersonaEntityNotFound() {
        Persona persona = new Persona();
        when(personaJpaRepository
                .existsById(Mockito.anyLong()))
                .thenReturn(false);
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.update(persona);
        assertTrue(personaAdapter.isEmpty());
    }

    @Test
    void deleteByIdPersonaEntityExitoso() {
        PersonaEntity personaEntity = new PersonaEntity(
                1L,
                "Paul",
                "Rodriguez",
                new Date(),
                "Masculino");
        Long id = 1L;
        when(personaJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(personaJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personaEntity));
        doNothing().when(personaJpaRepository).deleteById(Mockito.anyLong());
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.deleteById(id);
        assertNotNull(personaAdapter);
        personaAdapter.map(personaActual -> {
            assertEquals(personaActual.getId(), id);
            return personaActual;
        });
    }

    @Test
    void deleteByIdPersonaEntityNotFound() {
        Long id = 1L;
        when(personaJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Optional<Persona> personaAdapter = personaJpaRepositoryAdapter.deleteById(id);
        assertTrue(personaAdapter.isEmpty());
    }
}