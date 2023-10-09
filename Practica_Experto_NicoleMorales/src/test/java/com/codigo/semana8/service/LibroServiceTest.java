package com.codigo.semana8.service;

import com.codigo.semana8.model.Libro;
import com.codigo.semana8.repository.LibroRepository;
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

class LibroServiceTest {
    @Mock
    LibroRepository libroRepository;

    @InjectMocks
    LibroService libroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        libroService = new LibroService(libroRepository);
    }

    @Test
    void obtenerTodosLibrosExitoso() {
        List<Libro> libroList = new ArrayList<>();
        Libro libro = new Libro(1L, "Cien años de soledad", new ArrayList<>());
        libroList.add(libro);
        when(libroRepository.findAll()).thenReturn(libroList);
        List<Libro> response = libroService.obtenerTodosLibros();
        assertNotNull(response);
        libroList.stream().map(
                libro1 -> {
                    assertTrue(
                            response.contains(new Libro(
                                    libro1.getId(),
                                    libro1.getTitulo(),
                                    libro1.getAutores()
                            ))
                    );
                    return libro1;
                }
        );
        assertEquals(libroList.size(), response.size());
    }

    @Test
    void obtenerLibroPorId_Exitoso() {
        Libro libro = new Libro(1L, "Cien años de soledad", new ArrayList<>());
        when(libroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(libro));
        Libro response = libroService.obtenerLibroPorId(libro.getId());
        assertEquals(libro.getId(), response.getId());
        assertEquals(libro.getTitulo(), response.getTitulo());
    }

    @Test
    void obtenerLibroPorId_NoEncontrado() {
        Long id = 1L;
        when(libroRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> libroService.obtenerLibroPorId(id));
    }

    @Test
    void obtenerLibroPorTitulo_ExitosoTituloFromRequestBody() throws JsonProcessingException {
        String titulo = "{\"titulo\": \"Cien años de soledad\"}";
        Libro libro = new Libro(1L, "Cien años de soledad", new ArrayList<>());
        when(libroRepository.findByTitulo(Mockito.anyString())).thenReturn(Optional.of(libro));
        Libro response = libroService.obtenerLibroPorTitulo(titulo);
        assertEquals(libro.getId(), response.getId());
        assertEquals(libro.getTitulo(), response.getTitulo());
    }

    @Test
    void obtenerLibroPorTitulo_ExitosoTituloFromRequestParam() throws JsonProcessingException {
        String titulo = "Cien años de soledad";
        Libro libro = new Libro(1L, "Cien años de soledad", new ArrayList<>());
        when(libroRepository.findByTitulo(Mockito.anyString())).thenReturn(Optional.of(libro));
        Libro response = libroService.obtenerLibroPorTitulo(titulo);
        assertEquals(libro.getId(), response.getId());
        assertEquals(libro.getTitulo(), response.getTitulo());
    }

    @Test
    void obtenerLibroPorTitulo_NoEncontradoTituloFromRequestParam() throws JsonProcessingException {
        String titulo = "{\"titulo\": \"Cien años de soledad\"}";
        when(libroRepository.findByTitulo(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> libroService.obtenerLibroPorTitulo(titulo));
    }

    @Test
    void obtenerLibroPorTitulo_NoEncontradoTituloFromRequestBody() throws JsonProcessingException {
        String titulo = "Cien años de soledad";
        when(libroRepository.findByTitulo(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> libroService.obtenerLibroPorTitulo(titulo));
    }

    @Test
    void crearLibro_Exitoso() {
        Libro libro = new Libro(null,"Cien años de soledad", null);
        Libro libroGuardado = new Libro(1L,"Cien años de soledad", new ArrayList<>());
        String idealResponse = "El libro con id " + libroGuardado.getId() + " se ha creado correctamente.";
        when(libroRepository.findByTitulo(Mockito.anyString())).thenReturn(Optional.empty());
        when(libroRepository.save(Mockito.any(Libro.class))).thenReturn(libroGuardado);
        String response = libroService.crearLibro(libro);
        assertEquals(idealResponse, response);
    }

    @Test
    void crearLibro_yaExiste() {
        Libro libro = new Libro(null,"Cien años de soledad", null);
        Libro libroEncontrado = new Libro(1L,"Cien años de soledad", new ArrayList<>());
        when(libroRepository.findByTitulo(Mockito.anyString())).thenReturn(Optional.of(libroEncontrado));
        assertThrows(RuntimeException.class, () -> libroService.crearLibro(libro));
    }

    @Test
    void actualizarLibro_Exitoso() {
        Libro libro = new Libro(1L, "Cien años de soledad", new ArrayList<>());
        Libro libroActualizado = new Libro(1L, "100 años de soledad", new ArrayList<>());
        String idealResponse = "El libro con id " + libro.getId() + "se ha actualizado correctamente.";
        when(libroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(libro));
        when(libroRepository.save(Mockito.any(Libro.class))).thenReturn(libroActualizado);
        String response = libroService.actualizarLibro(libro.getId(), libroActualizado);
        assertEquals(idealResponse, response);
    }

    @Test
    void actualizarLibro_NoEncontrado() {
        Libro libroActualizado = new Libro(null, "100 años de soledad", new ArrayList<>());
        Long id = 1L;
        when(libroRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> libroService.actualizarLibro(id, libroActualizado));
    }

    @Test
    void eliminarLibro_Exitoso() {
        Libro libro = new Libro(1L, "Cien años de soledad", new ArrayList<>());
        String idealResponse = "El libro con id " + libro.getId() + " se ha eliminado correctamente.";
        when(libroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(libro));
        doNothing().when(libroRepository).delete(Mockito.any(Libro.class));
        String response = libroService.eliminarLibro(libro.getId());
        assertEquals(idealResponse, response);
    }

    @Test
    void eliminarLibro_NoEncontrado() {
        Long id = 1L;
        when(libroRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> libroService.eliminarLibro(id));
    }
}