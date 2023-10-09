package com.codigo.semana8.controller;

import com.codigo.semana8.model.Autor;
import com.codigo.semana8.service.AutorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/autores")
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores() {
        return new ResponseEntity<>(autorService.obtenerTodosAutores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id) {
        return new ResponseEntity<>(autorService.obtenerAutorPorId(id), HttpStatus.OK);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<Autor> obtenerAutorPorNombreParam(@RequestParam(value = "nombre") String nombre) throws JsonProcessingException {
        return new ResponseEntity<>(autorService.obtenerAutorPorNombre(nombre), HttpStatus.OK);
    }

    @PostMapping ("/buscarPorNombre")
    public ResponseEntity<Autor> obtenerAutorPorNombreBody(@RequestBody String nombre) throws JsonProcessingException {
        return new ResponseEntity<>(autorService.obtenerAutorPorNombre(nombre), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crearAutor(@RequestBody Autor autor) {
        return new ResponseEntity<>(autorService.crearAutor(autor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        return new ResponseEntity<>(autorService.actualizarAutor(id, autor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Long id) {
        return new ResponseEntity<>(autorService.eliminarAutor(id), HttpStatus.OK);
    }
}