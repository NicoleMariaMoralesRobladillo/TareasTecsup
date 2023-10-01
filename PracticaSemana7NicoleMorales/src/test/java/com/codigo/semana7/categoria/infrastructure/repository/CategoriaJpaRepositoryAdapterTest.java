package com.codigo.semana7.categoria.infrastructure.repository;

import com.codigo.semana7.categoria.domain.model.Categoria;
import com.codigo.semana7.categoria.infrastructure.entity.CategoriaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CategoriaJpaRepositoryAdapterTest {
    @Mock
    CategoriaJpaRepository categoriaJpaRepository;

    @InjectMocks
    CategoriaJpaRepositoryAdapter categoriaJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoriaJpaRepositoryAdapter = new CategoriaJpaRepositoryAdapter(categoriaJpaRepository);
    }

    @Test
    void saveCategoriaEntityExitoso() {
        // Lo que enviamos al método de la clase que estamos probando
        Categoria categoria = new Categoria(
                1L,
                "Electrodoméstico",
                1);

        // Lo que enviamos a BD como simulación
        CategoriaEntity categoriaEntity = new CategoriaEntity(
                1L,
                "Electrodoméstico",
                1);

        // Simulando comportamiento
        when(categoriaJpaRepository.save(
                Mockito.any(CategoriaEntity.class))).thenReturn(categoriaEntity);

        Categoria categoriaAdapter = categoriaJpaRepositoryAdapter.save(categoria);

        assertNotNull(categoriaAdapter);
        assertEquals(categoria.getId(), categoriaAdapter.getId());
        assertEquals(categoria.getNombre(), categoriaAdapter.getNombre());
        assertEquals(categoria.getEstado(), categoriaAdapter.getEstado());
    }

    @Test
    void findAllCategoriaEntityExitoso() {
        CategoriaEntity categoriaEntity = new CategoriaEntity(
                1L,
                "Electrodoméstico",
                1);
        List<CategoriaEntity> categoriaEntityList = new ArrayList<>();
        categoriaEntityList.add(categoriaEntity);
        Categoria categoria = new Categoria(
                1L,
                "Electrodoméstico",
                1);
        List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(categoria);
        when(categoriaJpaRepository.findAll()).thenReturn(categoriaEntityList);
        List<Categoria> categoriaAdapterList = categoriaJpaRepositoryAdapter.findAll();
        assertNotNull(categoriaAdapterList);
        categoriaList.stream().map(
                categoria1 -> {
                    assertTrue(
                            categoriaAdapterList.contains(new Categoria(
                                    categoria1.getId(),
                                    categoria1.getNombre(),
                                    categoria1.getEstado()
                            ))
                    );
                    return categoria1;
                }
        );
        assertEquals(categoriaList.size(), categoriaAdapterList.size());
    }

    @Test
    void findAllCategoriaEntityEmptyList() {
        List<CategoriaEntity> categoriaEntityList = new ArrayList<>();
        List<Categoria> categoriaList = new ArrayList<>();
        when(categoriaJpaRepository.findAll()).thenReturn(categoriaEntityList);
        List<Categoria> categoriaAdapterList = categoriaJpaRepositoryAdapter.findAll();
        assertNotNull(categoriaAdapterList);
        assertTrue(categoriaAdapterList.isEmpty());
        assertEquals(categoriaList, categoriaAdapterList);
    }

    @Test
    void findByIdCategoriaEntityExitoso() {
        CategoriaEntity categoriaEntity = new CategoriaEntity(
                1L,
                "Electrodoméstico",
                1);
        Categoria categoria = new Categoria(
                1L,
                "Electrodoméstico",
                1);
        when(categoriaJpaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(categoriaEntity));
        Optional<Categoria> categoriaAdapter = categoriaJpaRepositoryAdapter.findById(categoria.getId());
        assertNotNull(categoriaAdapter);
        categoriaAdapter.map(categoriaActual -> {
            assertEquals(categoria.getId(), categoriaActual.getId());
            assertEquals(categoria.getNombre(), categoriaActual.getNombre());
            assertEquals(categoria.getEstado(), categoriaActual.getEstado());
            return categoriaActual;
        });
    }

    @Test
    void findByIdCategoriaEntityNotFound() {
        Long id = 1L;
        when(categoriaJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<Categoria> categoriaAdapter = categoriaJpaRepositoryAdapter.findById(id);
        assertTrue(categoriaAdapter.isEmpty());
    }

    @Test
    void updateCategoriaEntityExitoso() {
        Categoria categoria = new Categoria(
                1L,
                "Electrodoméstico",
                1);
        CategoriaEntity categoriaEntity = new CategoriaEntity(
                1L,
                "Electrodoméstico",
                1);
        when(categoriaJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(categoriaJpaRepository.save(Mockito.any(CategoriaEntity.class))).thenReturn(categoriaEntity);
        Optional<Categoria> categoriaAdapter = categoriaJpaRepositoryAdapter.update(categoria);
        assertNotNull(categoriaAdapter);
        categoriaAdapter.map(
                categoriaActual -> {
                    assertEquals(categoria.getId(), categoriaActual.getId());
                    assertEquals(categoria.getNombre(), categoriaActual.getNombre());
                    assertEquals(categoria.getEstado(), categoriaActual.getEstado());
                    return categoriaActual;
                }
        );
    }

    @Test
    void updateCategoriaEntityNotFound() {
        Categoria categoria = new Categoria();
        when(categoriaJpaRepository
                .existsById(Mockito.anyLong()))
                .thenReturn(false);
        Optional<Categoria> categoriaAdapter = categoriaJpaRepositoryAdapter.update(categoria);
        assertTrue(categoriaAdapter.isEmpty());
    }

    @Test
    void deleteByIdCategoriaEntityExitoso() {
        CategoriaEntity categoriaEntity = new CategoriaEntity(
                1L,
                "Electrodoméstico",
                1);
        Categoria categoria = new Categoria(
                1L,
                "Electrodoméstico",
                1);
        when(categoriaJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(categoriaJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categoriaEntity));
        doNothing().when(categoriaJpaRepository).deleteById(Mockito.anyLong());
        Optional<Categoria> categoriaAdapter = categoriaJpaRepositoryAdapter.deleteById(categoria.getId());
        assertNotNull(categoriaAdapter);
        categoriaAdapter.map(categoriaActual -> {
            assertEquals(categoria.getId(), categoriaActual.getId());
            assertEquals(categoria.getNombre(), categoriaActual.getNombre());
            assertEquals(categoria.getEstado(), categoriaActual.getEstado());
            return categoriaActual;
        });
    }

    @Test
    void deleteByIdCategoriaEntityNotFound() {
        Long id = 1L;
        when(categoriaJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Optional<Categoria> categoriaAdapter = categoriaJpaRepositoryAdapter.deleteById(id);
        assertTrue(categoriaAdapter.isEmpty());
    }
}