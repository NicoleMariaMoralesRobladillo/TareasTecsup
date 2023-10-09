package com.codigo.semana8.service;

import com.codigo.semana8.model.Autor;
import com.codigo.semana8.repository.AutorRepository;
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

class AutorServiceTest {
    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    AutorService autorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        autorService = new AutorService(autorRepository);
    }

    @Test
    void obtenerTodosAutoresExitoso() {
        List<Autor> autorList = new ArrayList<>();
        Autor autor = new Autor(1L, "Jesus", new ArrayList<>());
        autorList.add(autor);
        when(autorRepository.findAll()).thenReturn(autorList);
        List<Autor> response = autorService.obtenerTodosAutores();
        assertNotNull(response);
        autorList.stream().map(
                autor1 -> {
                    assertTrue(
                            response.contains(new Autor(
                                    autor1.getId(),
                                    autor1.getNombre(),
                                    autor1.getLibros()
                            ))
                    );
                    return autor1;
                }
        );
        assertEquals(autorList.size(), response.size());
    }

    @Test
    void obtenerAutorPorId_Exitoso() {
        Autor autor = new Autor(1L, "Jesus", new ArrayList<>());
        when(autorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(autor));
        Autor response = autorService.obtenerAutorPorId(autor.getId());
        assertEquals(autor.getId(), response.getId());
        assertEquals(autor.getNombre(), response.getNombre());
    }

    @Test
    void obtenerAutorPorId_NoEncontrado() {
        Long id = 1L;
        when(autorRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> autorService.obtenerAutorPorId(id));
    }

    @Test
    void obtenerAutorPorNombre_ExitosoNombreFromRequestBody() throws JsonProcessingException {
        String nombre = "{\"nombre\": \"Nicole\"}";
        Autor autor = new Autor(1L, "Nicole", new ArrayList<>());
        when(autorRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(autor));
        Autor response = autorService.obtenerAutorPorNombre(nombre);
        assertEquals(autor.getId(), response.getId());
        assertEquals(autor.getNombre(), response.getNombre());
    }

    @Test
    void obtenerAutorPorNombre_ExitosoNombreFromRequestParam() throws JsonProcessingException {
        String nombre = "Nicole";
        Autor autor = new Autor(1L, "Nicole", new ArrayList<>());
        when(autorRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(autor));
        Autor response = autorService.obtenerAutorPorNombre(nombre);
        assertEquals(autor.getId(), response.getId());
        assertEquals(autor.getNombre(), response.getNombre());
    }

    @Test
    void obtenerAutorPorNombre_NoEncontradoNombreFromRequestParam() throws JsonProcessingException {
        String nombre = "{\"nombre\": \"Nicole\"}";
        when(autorRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> autorService.obtenerAutorPorNombre(nombre));
    }

    @Test
    void obtenerAutorPorNombre_NoEncontradoNombreFromRequestBody() throws JsonProcessingException {
        String nombre = "Nicole";
        when(autorRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> autorService.obtenerAutorPorNombre(nombre));
    }

    @Test
    void crearAutor_Exitoso() {
        Autor autor = new Autor(null,"Nicole", null);
        Autor autorGuardado = new Autor(1L,"Nicole", new ArrayList<>());
        String idealResponse = "El autor con id " + autorGuardado.getId() + " se ha creado correctamente.";
        when(autorRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.empty());
        when(autorRepository.save(Mockito.any(Autor.class))).thenReturn(autorGuardado);
        String response = autorService.crearAutor(autor);
        assertEquals(idealResponse, response);
    }

    @Test
    void crearAutor_yaExiste() {
        Autor autor = new Autor(null,"Nicole", null);
        Autor autorEncontrado = new Autor(1L,"Nicole", new ArrayList<>());
        when(autorRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(autorEncontrado));
        assertThrows(RuntimeException.class, () -> autorService.crearAutor(autor));
    }

    @Test
    void actualizarAutor_Exitoso() {
        Autor autor = new Autor(1L, "Jesus", new ArrayList<>());
        Autor autorActualizado = new Autor(1L, "Jaime", new ArrayList<>());
        String idealResponse = "El autor con id " + autor.getId() + "se ha actualizado correctamente.";
        when(autorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(autor));
        when(autorRepository.save(Mockito.any(Autor.class))).thenReturn(autorActualizado);
        String response = autorService.actualizarAutor(autor.getId(), autorActualizado);
        assertEquals(idealResponse, response);
    }

    @Test
    void actualizarAutor_NoEncontrado() {
        Autor autorActualizado = new Autor(null, "Jaime", new ArrayList<>());
        Long id = 1L;
        when(autorRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> autorService.actualizarAutor(id, autorActualizado));
    }

    @Test
    void eliminarAutor_Exitoso() {
        Autor autor = new Autor(1L, "Jesus", new ArrayList<>());
        String idealResponse = "El autor con id " + autor.getId() + " se ha eliminado correctamente.";
        when(autorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(autor));
        doNothing().when(autorRepository).delete(Mockito.any(Autor.class));
        String response = autorService.eliminarAutor(autor.getId());
        assertEquals(idealResponse, response);
    }

    @Test
    void eliminarAutor_NoEncontrado() {
        Long id = 1L;
        when(autorRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> autorService.eliminarAutor(id));
    }
}