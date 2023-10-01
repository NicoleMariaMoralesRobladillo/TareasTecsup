package com.codigo.semana7.proveedor.infrastructure.config;

import com.codigo.semana7.proveedor.application.service.ProveedorService;
import com.codigo.semana7.proveedor.application.usecase.ProveedorUseCaseImpl;
import com.codigo.semana7.proveedor.domain.ports.out.ProveedorRepositoryPort;
import com.codigo.semana7.proveedor.infrastructure.repository.ProveedorJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProveedorApplicationConfig {
    @Bean
    public ProveedorService proveedorService(ProveedorRepositoryPort proveedorRepositoryPort) {
        return new ProveedorService(new ProveedorUseCaseImpl(proveedorRepositoryPort));
    }

    @Bean
    public ProveedorRepositoryPort proveedorRepositoryPort(
            ProveedorJpaRepositoryAdapter proveedorJpaRepositoryAdapter) {
        return proveedorJpaRepositoryAdapter;
    }
}
