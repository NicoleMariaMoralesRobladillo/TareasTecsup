package com.codigo.semana7.usuario.infrastructure.repository;

import com.codigo.semana7.usuario.domain.model.Usuario;
import com.codigo.semana7.usuario.domain.ports.out.UsuarioRepositoryPort;
import com.codigo.semana7.usuario.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJpaRepositoryAdapter implements UsuarioRepositoryPort {
    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioJpaRepositoryAdapter(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
        UsuarioEntity saveUsuarioEntity = usuarioJpaRepository.save(usuarioEntity);
        return saveUsuarioEntity.toDomainModel();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id).map(UsuarioEntity::toDomainModel);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario) {
        if (usuarioJpaRepository.existsById(usuario.getId())) {
            UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
            UsuarioEntity updateUsuarioEntity = usuarioJpaRepository.save(usuarioEntity);
            return Optional.of(updateUsuarioEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> deleteById(Long id) {
        if (usuarioJpaRepository.existsById(id)) {
            Optional<Usuario> deleteUsuario = findById(id);
            usuarioJpaRepository.deleteById(id);
            return deleteUsuario;
        }
        return Optional.empty();
    }
}
