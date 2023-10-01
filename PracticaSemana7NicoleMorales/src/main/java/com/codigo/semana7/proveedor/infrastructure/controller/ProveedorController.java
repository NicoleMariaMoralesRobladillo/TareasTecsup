package com.codigo.semana7.proveedor.infrastructure.controller;

import com.codigo.semana7.proveedor.application.service.ProveedorService;
import com.codigo.semana7.proveedor.domain.model.Proveedor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor createProveedor = proveedorService.crearProveedor(proveedor);
        return new ResponseEntity<>(createProveedor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> getProveedores() {
        return new ResponseEntity<>(proveedorService.getProveedores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long id) {
        return proveedorService.getProveedor(id)
                .map(proveedor -> new ResponseEntity<>(proveedor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Proveedor> updateProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.updateProveedor(proveedor)
                .map(proveedorActualizada -> new ResponseEntity<>(proveedorActualizada, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proveedor> deleteProveedorById(@PathVariable Long id) {
        return proveedorService.deleteProveedor(id)
                .map(proveedor -> new ResponseEntity<>(proveedor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
