package com.codigo.semana7.usuario.infrastructure.config;

import com.codigo.semana7.usuario.application.service.UsuarioService;
import com.codigo.semana7.usuario.application.usecase.UsuarioUseCaseImpl;
import com.codigo.semana7.usuario.domain.ports.out.UsuarioRepositoryPort;
import com.codigo.semana7.usuario.infrastructure.repository.UsuarioJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigUsuario {
    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new UsuarioService(new UsuarioUseCaseImpl(usuarioRepositoryPort));
    }

    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(
            UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter) {
        return usuarioJpaRepositoryAdapter;
    }
}
