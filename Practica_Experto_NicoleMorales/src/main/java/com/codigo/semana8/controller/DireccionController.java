package com.codigo.semana8.controller;

import com.codigo.semana8.model.Direccion;
import com.codigo.semana8.service.DireccionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/direcciones")
public class DireccionController {
    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping
    public ResponseEntity<List<Direccion>> listarDirecciones() {
        return new ResponseEntity<>(direccionService.obtenerTodasDirecciones(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtenerDireccionPorId(@PathVariable Long id) {
        return new ResponseEntity<>(direccionService.obtenerDireccionPorId(id), HttpStatus.OK);
    }

    @GetMapping("/buscarPorCalle")
    public ResponseEntity<Direccion> obtenerDireccionPorCalleParam(@RequestParam(value = "calle") String calle) throws JsonProcessingException {
        return new ResponseEntity<>(direccionService.obtenerDireccionPorCalle(calle), HttpStatus.OK);
    }

    @PostMapping ("/buscarPorCalle")
    public ResponseEntity<Direccion> obtenerDireccionPorCalleBody(@RequestBody String calle) throws JsonProcessingException {
        return new ResponseEntity<>(direccionService.obtenerDireccionPorCalle(calle), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crearDireccion(@RequestBody Direccion direccion) {
        return new ResponseEntity<>(direccionService.crearDireccion(direccion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        return new ResponseEntity<>(direccionService.actualizarDireccion(id, direccion), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDireccion(@PathVariable Long id) {
        return new ResponseEntity<>(direccionService.eliminarDireccion(id), HttpStatus.OK);
    }
}