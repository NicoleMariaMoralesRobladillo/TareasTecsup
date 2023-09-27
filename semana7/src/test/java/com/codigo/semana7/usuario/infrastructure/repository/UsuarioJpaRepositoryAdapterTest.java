package com.codigo.semana7.usuario.infrastructure.repository;

import com.codigo.semana7.persona.domain.model.Persona;
import com.codigo.semana7.persona.infrastructure.entity.PersonaEntity;
import com.codigo.semana7.usuario.domain.model.Usuario;
import com.codigo.semana7.usuario.infrastructure.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UsuarioJpaRepositoryAdapterTest {
    @Mock
    UsuarioJpaRepository usuarioJpaRepository;

    @InjectMocks
    UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioJpaRepositoryAdapter = new UsuarioJpaRepositoryAdapter(usuarioJpaRepository);
    }

    @Test
    void saveUsuarioEntityExitoso() {
        // Lo que enviamos al método de la clase que estamos probando
        Usuario usuario = new Usuario(
                1L,
                "Paul",
                "1234",
                "paul@gmail.com",
                new Persona());

        // Lo que enviamos a BD como simulación
        UsuarioEntity usuarioEntity = new UsuarioEntity(
                1L,
                "Paul",
                "1234",
                "paul@gmail.com",
                new PersonaEntity());

        // Simulando comportamiento
        when(usuarioJpaRepository.save(
                Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario usuarioAdapter = usuarioJpaRepositoryAdapter.save(usuario);

        assertNotNull(usuarioAdapter);
        assertEquals(usuario.getId(), usuarioAdapter.getId());
        assertEquals(usuario.getNombreUsuario(), usuarioAdapter.getNombreUsuario());
    }

    @Test
    void findByIdUsuarioEntityExitoso() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(
                1L,
                "Paul",
                "1234",
                "paul@gmail.com",
                new PersonaEntity());
        Long id = 1L;
        when(usuarioJpaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(usuarioEntity));
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.findById(id);
        assertNotNull(usuarioAdapter);
        usuarioAdapter.map(usuario -> {
            Long usuarioId = usuario.getId();
            assertEquals(usuarioId, id);
            return usuarioId;
        });
    }

    @Test
    void findByIdUsuarioEntityNotFound() {
        Long id = 1L;
        when(usuarioJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.findById(id);
        assertTrue(usuarioAdapter.isEmpty());
    }

    @Test
    void updateUsuarioEntityExitoso() {
        Usuario usuario = new Usuario(
                1L,
                "Paul",
                "1234",
                "paul@gmail.com",
                new Persona());
        UsuarioEntity usuarioEntity = new UsuarioEntity(
                1L,
                "Paul",
                "1234",
                "paul@gmail.com",
                new PersonaEntity());
        when(usuarioJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.update(usuario);
        assertNotNull(usuarioAdapter);
        usuarioAdapter.map(
                usuarioActual -> {
                    assertEquals(usuario.getId(), usuarioActual.getId());
                    assertEquals(usuario.getNombreUsuario(), usuarioActual.getNombreUsuario());
                    return usuarioActual;
                }
        );
    }

    @Test
    void updateUsuarioEntityNotFound() {
        Usuario usuario = new Usuario();
        when(usuarioJpaRepository
                .existsById(Mockito.anyLong()))
                .thenReturn(false);
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.update(usuario);
        assertTrue(usuarioAdapter.isEmpty());
    }

    @Test
    void deleteByIdUsuarioEntityExitoso() {
        UsuarioEntity usuarioEntity = new UsuarioEntity(
                1L,
                "Paul",
                "1234",
                "paul@gmail.com",
                new PersonaEntity());
        Long id = 1L;
        when(usuarioJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(usuarioJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(usuarioEntity));
        doNothing().when(usuarioJpaRepository).deleteById(Mockito.anyLong());
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.deleteById(id);
        assertNotNull(usuarioAdapter);
        usuarioAdapter.map(usuarioActual -> {
            assertEquals(usuarioActual.getId(), id);
            return usuarioActual;
        });
    }

    @Test
    void deleteByIdUsuarioEntityNotFound() {
        Long id = 1L;
        when(usuarioJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Optional<Usuario> usuarioAdapter = usuarioJpaRepositoryAdapter.deleteById(id);
        assertTrue(usuarioAdapter.isEmpty());
    }
}