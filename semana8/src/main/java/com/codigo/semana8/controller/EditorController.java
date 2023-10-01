package com.codigo.semana8.controller;

import com.codigo.semana8.model.Editor;
import com.codigo.semana8.service.EditorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("api/editores")
public class EditorController {
    private final EditorService editorService;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @GetMapping
    public ResponseEntity<List<Editor>> listarEditores() {
        return new ResponseEntity<>(editorService.obtenerTodosEditores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editor> obtenerEditorPorId(@PathVariable Long id) {
        return new ResponseEntity<>(editorService.obtenerEditorPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Editor> crearEditor(@RequestBody Editor editor) {
        return new ResponseEntity<>(editorService.crearEditor(editor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editor> actualizarEditor(@PathVariable Long id, @RequestBody Editor editor) {
        return new ResponseEntity<>(editorService.actualizarEditor(id, editor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Editor> eliminarEditor(@PathVariable Long id) {
        return new ResponseEntity<>(editorService.eliminarEditorLogicamente(id), HttpStatus.OK);
    }
}