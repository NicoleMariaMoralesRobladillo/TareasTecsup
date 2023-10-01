package com.codigo.semana7.categoria.infrastructure.controller;

import com.codigo.semana7.categoria.application.service.CategoriaService;
import com.codigo.semana7.categoria.domain.model.Categoria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        Categoria createCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(createCategoria, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias() {
        return new ResponseEntity<>(categoriaService.getCategorias(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        return categoriaService.getCategoria(id)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria) {
        return categoriaService.updateCategoria(categoria)
                .map(categoriaActualizada -> new ResponseEntity<>(categoriaActualizada, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> deleteCategoriaById(@PathVariable Long id) {
        return categoriaService.deleteCategoria(id)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
