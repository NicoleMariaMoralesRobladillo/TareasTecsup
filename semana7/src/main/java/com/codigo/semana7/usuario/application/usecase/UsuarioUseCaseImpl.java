package com.codigo.semana7.usuario.application.usecase;

import com.codigo.semana7.usuario.domain.model.Usuario;
import com.codigo.semana7.usuario.domain.ports.in.UsuarioUseCase;
import com.codigo.semana7.usuario.domain.ports.out.UsuarioRepositoryPort;

import java.util.Optional;

public class UsuarioUseCaseImpl implements UsuarioUseCase {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositoryPort.save(usuario);
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        return usuarioRepositoryPort.findById(id);
    }

    @Override
    public Optional<Usuario> updateUsuario(Usuario usuario) {
        return usuarioRepositoryPort.update(usuario);
    }

    @Override
    public Optional<Usuario> deleteUsuario(Long id) {
        return usuarioRepositoryPort.deleteById(id);
    }
}
