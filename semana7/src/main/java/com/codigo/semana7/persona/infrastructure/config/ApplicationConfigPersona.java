package com.codigo.semana7.persona.infrastructure.config;

import com.codigo.semana7.persona.application.service.PersonaService;
import com.codigo.semana7.persona.application.usecase.PersonaUseCaseImpl;
import com.codigo.semana7.persona.domain.ports.out.PersonaRepositoryPort;
import com.codigo.semana7.persona.infrastructure.repository.PersonaJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigPersona {
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
