package com.codigo.semana7.categoria.infrastructure.config;

import com.codigo.semana7.categoria.application.service.CategoriaService;
import com.codigo.semana7.categoria.application.usecase.CategoriaUseCaseImpl;
import com.codigo.semana7.categoria.domain.ports.out.CategoriaRepositoryPort;
import com.codigo.semana7.categoria.infrastructure.repository.CategoriaJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaApplicationConfig {
    @Bean
    public CategoriaService categoriaService(CategoriaRepositoryPort categoriaRepositoryPort) {
        return new CategoriaService(new CategoriaUseCaseImpl(categoriaRepositoryPort));
    }

    @Bean
    public CategoriaRepositoryPort categoriaRepositoryPort(
            CategoriaJpaRepositoryAdapter categoriaJpaRepositoryAdapter) {
        return categoriaJpaRepositoryAdapter;
    }
}
