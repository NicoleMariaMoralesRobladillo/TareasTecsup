package com.codigo.semana7.infrastructure.config;

import com.codigo.semana7.application.service.PersonaService;
import com.codigo.semana7.application.usecase.PersonaUseCaseImpl;
import com.codigo.semana7.domain.ports.out.PersonaRepositoryPort;
import com.codigo.semana7.infrastructure.repository.PersonaJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public PersonaService personaService(PersonaRepositoryPort personaRepositoryPort) {
        return new PersonaService(new PersonaUseCaseImpl(personaRepositoryPort));
    }

    @Bean
    public PersonaRepositoryPort personaRepositoryPort(
            PersonaJpaRepositoryAdapter personaJpaRepositoryAdapter) {
        return personaJpaRepositoryAdapter;
    }
}
