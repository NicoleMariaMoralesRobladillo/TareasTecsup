package com.codigo.semana8.service;

import com.codigo.semana8.model.Direccion;
import com.codigo.semana8.repository.DireccionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {
    private final DireccionRepository direccionRepository;

    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public List<Direccion> obtenerTodasDirecciones() {
        return direccionRepository.findAll();
    }

    public Direccion obtenerDireccionPorId(Long id) {
        Optional<Direccion> direccion = direccionRepository.findById(id);
        if (direccion.isPresent()) {
            return direccion.get();
        }
        else {
            throw new RuntimeException("Dirección no encontrada.");
        }
    }

    public Direccion obtenerDireccionPorCalle(String calle) throws JsonProcessingException {
        Optional<Direccion> direccionEncontrada;
        if (calle.contains("{")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Direccion direccion = objectMapper.readValue(calle, Direccion.class);
            calle = direccion.getCalle();
        }
        direccionEncontrada = direccionRepository.findByCalle(calle);
        if (direccionEncontrada.isPresent()) {
            return direccionEncontrada.get();
        }
        else {
            throw new RuntimeException("Dirección no encontrada.");
        }
    }

    public String crearDireccion(Direccion direccion) {
        Optional<Direccion> direccionEncontrada = direccionRepository.findByCalle(direccion.getCalle());
        Direccion direccionGuardada;
        if (direccionEncontrada.isEmpty()) {
            direccion.setId(null);
            direccionGuardada = direccionRepository.save(direccion);
            return "La dirección con id " + direccionGuardada.getId() + " se ha creado correctamente.";
        }
        else {
            throw new RuntimeException("La dirección ya existe.");
        }
    }

    public String actualizarDireccion(Long id, Direccion direccion) {
        Direccion direccionExistente = obtenerDireccionPorId(id);
        direccionExistente.setCalle(direccion.getCalle());
        direccionRepository.save(direccionExistente);
        return "La dirección con id " + direccionExistente.getId() + "se ha actualizado correctamente.";
    }

    public String eliminarDireccion(Long id) {
        Direccion direccionExistente = obtenerDireccionPorId(id);
        direccionRepository.delete(direccionExistente);
        return "La dirección con id " + direccionExistente.getId() + " se ha eliminado correctamente.";
    }
}