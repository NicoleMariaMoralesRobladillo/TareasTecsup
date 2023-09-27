package com.codigo.semana7.usuario.domain.ports.in;

import com.codigo.semana7.usuario.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioUseCase {
    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> getUsuario(Long id);
    Optional<Usuario> updateUsuario(Usuario usuario);
    Optional<Usuario> deleteUsuario(Long id);
}
