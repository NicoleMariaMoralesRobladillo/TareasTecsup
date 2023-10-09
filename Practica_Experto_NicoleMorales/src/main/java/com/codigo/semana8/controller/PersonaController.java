package com.codigo.semana8.controller;

import com.codigo.semana8.model.Persona;
import com.codigo.semana8.service.PersonaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personas")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> listarPersonas() {
        return new ResponseEntity<>(personaService.obtenerTodasPersonas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(personaService.obtenerPersonaPorId(id), HttpStatus.OK);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<Persona> obtenerPersonaPorNombreParam(@RequestParam(value = "nombre") String nombre) throws JsonProcessingException {
        return new ResponseEntity<>(personaService.obtenerPersonaPorNombre(nombre), HttpStatus.OK);
    }

    @PostMapping ("/buscarPorNombre")
    public ResponseEntity<Persona> obtenerPersonaPorNombreBody(@RequestBody String nombre) throws JsonProcessingException {
        return new ResponseEntity<>(personaService.obtenerPersonaPorNombre(nombre), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crearPersona(@RequestBody Persona persona) {
        return new ResponseEntity<>(personaService.crearPersona(persona), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
        return new ResponseEntity<>(personaService.actualizarPersona(id, persona), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long id) {
        return new ResponseEntity<>(personaService.eliminarPersona(id), HttpStatus.OK);
    }
}