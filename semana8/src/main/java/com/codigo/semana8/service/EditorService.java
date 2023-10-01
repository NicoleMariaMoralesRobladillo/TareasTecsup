package com.codigo.semana8.service;

import com.codigo.semana8.model.Editor;
import com.codigo.semana8.repository.EditorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorService {
    private final EditorRepository editorRepository;
    public EditorService(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }

    public List<Editor> obtenerTodosEditores() {
        return editorRepository.findAll();
    }

    public Editor obtenerEditorPorId(Long id) {
        Optional<Editor> editor = editorRepository.findById(id);
        if (editor.isPresent()) {
            return editor.get();
        }
        else {
            throw new RuntimeException("Editor no encontrado.");
        }
    }

    public Editor crearEditor(Editor editor) {
        editor.setId(null);
        return editorRepository.save(editor);
    }

    public Editor actualizarEditor(Long id, Editor editor) {
        Editor editorExistente = obtenerEditorPorId(id);
        editorExistente.setNombre(editor.getNombre());
        editorExistente.setEstado(editor.getEstado());
        return editorRepository.save(editorExistente);
    }

    public Editor eliminarEditorLogicamente(Long id){
        Editor editorExistente = obtenerEditorPorId(id);
        editorExistente.setEstado(0);
        return editorRepository.save(editorExistente);
    }
}