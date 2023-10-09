package com.codigo.semana8.service;

import com.codigo.semana8.model.Persona;
import com.codigo.semana8.repository.PersonaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class PersonaServiceTest {
    @Mock
    PersonaRepository personaRepository;

    @InjectMocks
    PersonaService personaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personaService = new PersonaService(personaRepository);
    }

    @Test
    void obtenerTodasPersonasExitoso() {
        List<Persona> personaList = new ArrayList<>();
        Persona persona = new Persona(1L, "Jesus", new ArrayList<>());
        personaList.add(persona);
        when(personaRepository.findAll()).thenReturn(personaList);
        List<Persona> response = personaService.obtenerTodasPersonas();
        assertNotNull(response);
        personaList.stream().map(
                persona1 -> {
                    assertTrue(
                            response.contains(new Persona(
                                    persona1.getId(),
                                    persona1.getNombre(),
                                    persona1.getDirecciones()
                            ))
                    );
                    return persona1;
                }
        );
        assertEquals(personaList.size(), response.size());
    }

    @Test
    void obtenerPersonaPorId_Exitoso() {
        Persona persona = new Persona(1L, "Jesus", new ArrayList<>());
        when(personaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(persona));
        Persona response = personaService.obtenerPersonaPorId(persona.getId());
        assertEquals(persona.getId(), response.getId());
        assertEquals(persona.getNombre(), response.getNombre());
    }

    @Test
    void obtenerPersonaPorId_NoEncontrado() {
        Long id = 1L;
        when(personaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> personaService.obtenerPersonaPorId(id));
    }

    @Test
    void obtenerPersonaPorNombre_ExitosoNombreFromRequestBody() throws JsonProcessingException {
        String nombre = "{\"nombre\": \"Nicole\"}";
        Persona persona = new Persona(1L, "Nicole", new ArrayList<>());
        when(personaRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(persona));
        Persona response = personaService.obtenerPersonaPorNombre(nombre);
        assertEquals(persona.getId(), response.getId());
        assertEquals(persona.getNombre(), response.getNombre());
    }

    @Test
    void obtenerPersonaPorNombre_ExitosoNombreFromRequestParam() throws JsonProcessingException {
        String nombre = "Nicole";
        Persona persona = new Persona(1L, "Nicole", new ArrayList<>());
        when(personaRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(persona));
        Persona response = personaService.obtenerPersonaPorNombre(nombre);
        assertEquals(persona.getId(), response.getId());
        assertEquals(persona.getNombre(), response.getNombre());
    }

    @Test
    void obtenerPersonaPorNombre_NoEncontradoNombreFromRequestParam() throws JsonProcessingException {
        String nombre = "{\"nombre\": \"Nicole\"}";
        when(personaRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> personaService.obtenerPersonaPorNombre(nombre));
    }

    @Test
    void obtenerPersonaPorNombre_NoEncontradoNombreFromRequestBody() throws JsonProcessingException {
        String nombre = "Nicole";
        when(personaRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> personaService.obtenerPersonaPorNombre(nombre));
    }

    @Test
    void crearPersona_Exitoso() {
        Persona persona = new Persona(null,"Nicole", null);
        Persona personaGuardado = new Persona(1L,"Nicole", new ArrayList<>());
        String idealResponse = "La persona con id " + personaGuardado.getId() + " se ha creado correctamente.";
        when(personaRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.empty());
        when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(personaGuardado);
        String response = personaService.crearPersona(persona);
        assertEquals(idealResponse, response);
    }

    @Test
    void crearPersona_yaExiste() {
        Persona persona = new Persona(null,"Nicole", null);
        Persona personaEncontrado = new Persona(1L,"Nicole", new ArrayList<>());
        when(personaRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(personaEncontrado));
        assertThrows(RuntimeException.class, () -> personaService.crearPersona(persona));
    }

    @Test
    void actualizarPersona_Exitoso() {
        Persona persona = new Persona(1L, "Jesus", new ArrayList<>());
        Persona personaActualizado = new Persona(1L, "Jaime", new ArrayList<>());
        String idealResponse = "La persona con id " + persona.getId() + "se ha actualizado correctamente.";
        when(personaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(persona));
        when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(personaActualizado);
        String response = personaService.actualizarPersona(persona.getId(), personaActualizado);
        assertEquals(idealResponse, response);
    }

    @Test
    void actualizarPersona_NoEncontrado() {
        Persona personaActualizado = new Persona(null, "Jaime", new ArrayList<>());
        Long id = 1L;
        when(personaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> personaService.actualizarPersona(id, personaActualizado));
    }

    @Test
    void eliminarPersona_Exitoso() {
        Persona persona = new Persona(1L, "Jesus", new ArrayList<>());
        String idealResponse = "La persona con id " + persona.getId() + " se ha eliminado correctamente.";
        when(personaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(persona));
        doNothing().when(personaRepository).delete(Mockito.any(Persona.class));
        String response = personaService.eliminarPersona(persona.getId());
        assertEquals(idealResponse, response);
    }

    @Test
    void eliminarPersona_NoEncontrado() {
        Long id = 1L;
        when(personaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> personaService.eliminarPersona(id));
    }
}