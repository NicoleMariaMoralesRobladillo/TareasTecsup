package com.codigo.semana7.usuario.application.service;

import com.codigo.semana7.usuario.domain.model.Usuario;
import com.codigo.semana7.usuario.domain.ports.in.UsuarioUseCase;

import java.util.Optional;

public class UsuarioService implements UsuarioUseCase {
    private final UsuarioUseCase usuarioUseCase;

    public UsuarioService(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioUseCase.crearUsuario(usuario);
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioUseCase.getUsuario(id);
    }

    @Override
    public Optional<Usuario> updateUsuario(Usuario usuario) {
        return usuarioUseCase.updateUsuario(usuario);
    }

    @Override
    public Optional<Usuario> deleteUsuario(Long id) {
        return usuarioUseCase.deleteUsuario(id);
    }
}
