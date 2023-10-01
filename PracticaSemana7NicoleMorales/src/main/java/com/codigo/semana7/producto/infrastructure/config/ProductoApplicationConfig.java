package com.codigo.semana7.producto.infrastructure.config;

import com.codigo.semana7.producto.application.service.ProductoService;
import com.codigo.semana7.producto.application.usecase.ProductoUseCaseImpl;
import com.codigo.semana7.producto.domain.ports.out.ProductoRepositoryPort;
import com.codigo.semana7.producto.infrastructure.repository.ProductoJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductoApplicationConfig {
    @Bean
    public ProductoService productoService(ProductoRepositoryPort productoRepositoryPort) {
        return new ProductoService(new ProductoUseCaseImpl(productoRepositoryPort));
    }

    @Bean
    public ProductoRepositoryPort productoRepositoryPort(
            ProductoJpaRepositoryAdapter productoJpaRepositoryAdapter) {
        return productoJpaRepositoryAdapter;
    }
}
