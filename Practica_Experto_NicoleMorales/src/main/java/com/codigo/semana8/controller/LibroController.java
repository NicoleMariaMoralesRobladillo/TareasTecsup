package com.codigo.semana8.controller;

import com.codigo.semana8.model.Libro;
import com.codigo.semana8.service.LibroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        return new ResponseEntity<>(libroService.obtenerTodosLibros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return new ResponseEntity<>(libroService.obtenerLibroPorId(id), HttpStatus.OK);
    }

    @GetMapping("/buscarPorTitulo")
    public ResponseEntity<Libro> obtenerLibroPorTituloParam(@RequestParam(value = "titulo") String titulo) throws JsonProcessingException {
        return new ResponseEntity<>(libroService.obtenerLibroPorTitulo(titulo), HttpStatus.OK);
    }

    @PostMapping ("/buscarPorTitulo")
    public ResponseEntity<Libro> obtenerLibroPorTituloBody(@RequestBody String titulo) throws JsonProcessingException {
        return new ResponseEntity<>(libroService.obtenerLibroPorTitulo(titulo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crearLibro(@RequestBody Libro libro) {
        return new ResponseEntity<>(libroService.crearLibro(libro), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return new ResponseEntity<>(libroService.actualizarLibro(id, libro), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLibro(@PathVariable Long id) {
        return new ResponseEntity<>(libroService.eliminarLibro(id), HttpStatus.OK);
    }
}