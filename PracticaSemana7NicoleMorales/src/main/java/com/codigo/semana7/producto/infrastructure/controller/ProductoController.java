package com.codigo.semana7.producto.infrastructure.controller;

import com.codigo.semana7.producto.application.service.ProductoService;
import com.codigo.semana7.producto.domain.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoController {
    private final ProductoService productoService;
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto createProducto = productoService.crearProducto(producto);
        return new ResponseEntity<>(createProducto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        return new ResponseEntity<>(productoService.getProductos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.getProducto(id)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto) {
        return productoService.updateProducto(producto)
                .map(productoActualizada -> new ResponseEntity<>(productoActualizada, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProductoById(@PathVariable Long id) {
        return productoService.deleteProducto(id)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
