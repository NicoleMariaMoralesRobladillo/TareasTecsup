package com.codigo.semana8.service;

import com.codigo.semana8.model.Direccion;
import com.codigo.semana8.model.Persona;
import com.codigo.semana8.repository.DireccionRepository;
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

class DireccionServiceTest {
    @Mock
    DireccionRepository direccionRepository;

    @InjectMocks
    DireccionService direccionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        direccionService = new DireccionService(direccionRepository);
    }

    @Test
    void obtenerTodasDireccionesExitoso() {
        List<Direccion> direccionList = new ArrayList<>();
        Direccion direccion = new Direccion(1L, "Las Flores", new Persona());
        direccionList.add(direccion);
        when(direccionRepository.findAll()).thenReturn(direccionList);
        List<Direccion> response = direccionService.obtenerTodasDirecciones();
        assertNotNull(response);
        direccionList.stream().map(
                direccion1 -> {
                    assertTrue(
                            response.contains(new Direccion(
                                    direccion1.getId(),
                                    direccion1.getCalle(),
                                    direccion1.getPersona()
                            ))
                    );
                    return direccion1;
                }
        );
        assertEquals(direccionList.size(), response.size());
    }

    @Test
    void obtenerDireccionPorId_Exitoso() {
        Direccion direccion = new Direccion(1L, "Las Flores", new Persona());
        when(direccionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(direccion));
        Direccion response = direccionService.obtenerDireccionPorId(direccion.getId());
        assertEquals(direccion.getId(), response.getId());
        assertEquals(direccion.getCalle(), response.getCalle());
    }

    @Test
    void obtenerDireccionPorId_NoEncontrado() {
        Long id = 1L;
        when(direccionRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> direccionService.obtenerDireccionPorId(id));
    }

    @Test
    void obtenerDireccionPorCalle_ExitosoCalleFromRequestBody() throws JsonProcessingException {
        String calle = "{\"calle\": \"Las Flores\"}";
        Direccion direccion = new Direccion(1L, "Las Flores", new Persona());
        when(direccionRepository.findByCalle(Mockito.anyString())).thenReturn(Optional.of(direccion));
        Direccion response = direccionService.obtenerDireccionPorCalle(calle);
        assertEquals(direccion.getId(), response.getId());
        assertEquals(direccion.getCalle(), response.getCalle());
    }

    @Test
    void obtenerDireccionPorCalle_ExitosoCalleFromRequestParam() throws JsonProcessingException {
        String calle = "Las Flores";
        Direccion direccion = new Direccion(1L, "Las Flores", new Persona());
        when(direccionRepository.findByCalle(Mockito.anyString())).thenReturn(Optional.of(direccion));
        Direccion response = direccionService.obtenerDireccionPorCalle(calle);
        assertEquals(direccion.getId(), response.getId());
        assertEquals(direccion.getCalle(), response.getCalle());
    }

    @Test
    void obtenerDireccionPorCalle_NoEncontradoCalleFromRequestParam() throws JsonProcessingException {
        String calle = "{\"calle\": \"Las Flores\"}";
        when(direccionRepository.findByCalle(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> direccionService.obtenerDireccionPorCalle(calle));
    }

    @Test
    void obtenerDireccionPorCalle_NoEncontradoCalleFromRequestBody() throws JsonProcessingException {
        String calle = "Las Flores";
        when(direccionRepository.findByCalle(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> direccionService.obtenerDireccionPorCalle(calle));
    }

    @Test
    void crearDireccion_Exitoso() {
        Direccion direccion = new Direccion(null,"Las Flores", null);
        Direccion direccionGuardado = new Direccion(1L,"Las Flores", new Persona());
        String idealResponse = "La dirección con id " + direccionGuardado.getId() + " se ha creado correctamente.";
        when(direccionRepository.findByCalle(Mockito.anyString())).thenReturn(Optional.empty());
        when(direccionRepository.save(Mockito.any(Direccion.class))).thenReturn(direccionGuardado);
        String response = direccionService.crearDireccion(direccion);
        assertEquals(idealResponse, response);
    }

    @Test
    void crearDireccion_yaExiste() {
        Direccion direccion = new Direccion(null,"Las Flores", null);
        Direccion direccionEncontrado = new Direccion(1L,"Las Flores", new Persona());
        when(direccionRepository.findByCalle(Mockito.anyString())).thenReturn(Optional.of(direccionEncontrado));
        assertThrows(RuntimeException.class, () -> direccionService.crearDireccion(direccion));
    }

    @Test
    void actualizarDireccion_Exitoso() {
        Direccion direccion = new Direccion(1L, "Las Flores", new Persona());
        Direccion direccionActualizado = new Direccion(1L, "Las Rosas", new Persona());
        String idealResponse = "La dirección con id " + direccion.getId() + "se ha actualizado correctamente.";
        when(direccionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(direccion));
        when(direccionRepository.save(Mockito.any(Direccion.class))).thenReturn(direccionActualizado);
        String response = direccionService.actualizarDireccion(direccion.getId(), direccionActualizado);
        assertEquals(idealResponse, response);
    }

    @Test
    void actualizarDireccion_NoEncontrado() {
        Direccion direccionActualizado = new Direccion(null, "Las Rosas", new Persona());
        Long id = 1L;
        when(direccionRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> direccionService.actualizarDireccion(id, direccionActualizado));
    }

    @Test
    void eliminarDireccion_Exitoso() {
        Direccion direccion = new Direccion(1L, "Las Flores", new Persona());
        String idealResponse = "La dirección con id " + direccion.getId() + " se ha eliminado correctamente.";
        when(direccionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(direccion));
        doNothing().when(direccionRepository).delete(Mockito.any(Direccion.class));
        String response = direccionService.eliminarDireccion(direccion.getId());
        assertEquals(idealResponse, response);
    }

    @Test
    void eliminarDireccion_NoEncontrado() {
        Long id = 1L;
        when(direccionRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> direccionService.eliminarDireccion(id));
    }
}