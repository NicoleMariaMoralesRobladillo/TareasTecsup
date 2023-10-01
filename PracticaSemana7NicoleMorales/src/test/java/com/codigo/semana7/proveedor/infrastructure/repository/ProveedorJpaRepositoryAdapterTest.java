package com.codigo.semana7.proveedor.infrastructure.repository;

import com.codigo.semana7.proveedor.domain.model.Proveedor;
import com.codigo.semana7.proveedor.infrastructure.entity.ProveedorEntity;
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

class ProveedorJpaRepositoryAdapterTest {
    @Mock
    ProveedorJpaRepository proveedorJpaRepository;

    @InjectMocks
    ProveedorJpaRepositoryAdapter proveedorJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        proveedorJpaRepositoryAdapter = new ProveedorJpaRepositoryAdapter(proveedorJpaRepository);
    }

    @Test
    void saveProveedorEntityExitoso() {
        // Lo que enviamos al método de la clase que estamos probando
        Proveedor proveedor = new Proveedor(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);

        // Lo que enviamos a BD como simulación
        ProveedorEntity proveedorEntity = new ProveedorEntity(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);

        // Simulando comportamiento
        when(proveedorJpaRepository.save(
                Mockito.any(ProveedorEntity.class))).thenReturn(proveedorEntity);

        Proveedor proveedorAdapter = proveedorJpaRepositoryAdapter.save(proveedor);

        assertNotNull(proveedorAdapter);
        assertEquals(proveedor.getId(), proveedorAdapter.getId());
        assertEquals(proveedor.getNombre(), proveedorAdapter.getNombre());
        assertEquals(proveedor.getDireccion(), proveedorAdapter.getDireccion());
        assertEquals(proveedor.getTelefono(), proveedorAdapter.getTelefono());
        assertEquals(proveedor.getEstado(), proveedorAdapter.getEstado());
    }

    @Test
    void findAllProveedorEntityExitoso() {
        ProveedorEntity proveedorEntity = new ProveedorEntity(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);
        List<ProveedorEntity> proveedorEntityList = new ArrayList<>();
        proveedorEntityList.add(proveedorEntity);
        Proveedor proveedor = new Proveedor(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);
        List<Proveedor> proveedorList = new ArrayList<>();
        proveedorList.add(proveedor);
        when(proveedorJpaRepository.findAll()).thenReturn(proveedorEntityList);
        List<Proveedor> proveedorAdapterList = proveedorJpaRepositoryAdapter.findAll();
        assertNotNull(proveedorAdapterList);
        proveedorList.stream().map(
                proveedor1 -> {
                    assertTrue(
                            proveedorAdapterList.contains(new Proveedor(
                                    proveedor1.getId(),
                                    proveedor1.getNombre(),
                                    proveedor1.getDireccion(),
                                    proveedor1.getTelefono(),
                                    proveedor1.getEstado()
                            ))
                    );
                    return proveedor1;
                }
        );
        assertEquals(proveedorList.size(), proveedorAdapterList.size());
    }

    @Test
    void findAllProveedorEntityEmptyList() {
        List<ProveedorEntity> proveedorEntityList = new ArrayList<>();
        List<Proveedor> proveedorList = new ArrayList<>();
        when(proveedorJpaRepository.findAll()).thenReturn(proveedorEntityList);
        List<Proveedor> proveedorAdapterList = proveedorJpaRepositoryAdapter.findAll();
        assertNotNull(proveedorAdapterList);
        assertTrue(proveedorAdapterList.isEmpty());
        assertEquals(proveedorList, proveedorAdapterList);
    }

    @Test
    void findByIdProveedorEntityExitoso() {
        ProveedorEntity proveedorEntity = new ProveedorEntity(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);
        Proveedor proveedor = new Proveedor(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);
        when(proveedorJpaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(proveedorEntity));
        Optional<Proveedor> proveedorAdapter = proveedorJpaRepositoryAdapter.findById(proveedor.getId());
        assertNotNull(proveedorAdapter);
        proveedorAdapter.map(proveedorActual -> {
            assertEquals(proveedor.getId(), proveedorActual.getId());
            assertEquals(proveedor.getNombre(), proveedorActual.getNombre());
            assertEquals(proveedor.getDireccion(), proveedorActual.getDireccion());
            assertEquals(proveedor.getTelefono(), proveedorActual.getTelefono());
            assertEquals(proveedor.getEstado(), proveedorActual.getEstado());
            return proveedorActual;
        });
    }

    @Test
    void findByIdProveedorEntityNotFound() {
        Long id = 1L;
        when(proveedorJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<Proveedor> proveedorAdapter = proveedorJpaRepositoryAdapter.findById(id);
        assertTrue(proveedorAdapter.isEmpty());
    }

    @Test
    void updateProveedorEntityExitoso() {
        Proveedor proveedor = new Proveedor(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);
        ProveedorEntity proveedorEntity = new ProveedorEntity(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);
        when(proveedorJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(proveedorJpaRepository.save(Mockito.any(ProveedorEntity.class))).thenReturn(proveedorEntity);
        Optional<Proveedor> proveedorAdapter = proveedorJpaRepositoryAdapter.update(proveedor);
        assertNotNull(proveedorAdapter);
        proveedorAdapter.map(
                proveedorActual -> {
                    assertEquals(proveedor.getId(), proveedorActual.getId());
                    assertEquals(proveedor.getNombre(), proveedorActual.getNombre());
                    assertEquals(proveedor.getDireccion(), proveedorActual.getDireccion());
                    assertEquals(proveedor.getTelefono(), proveedorActual.getTelefono());
                    assertEquals(proveedor.getEstado(), proveedorActual.getEstado());
                    return proveedorActual;
                }
        );
    }

    @Test
    void updateProveedorEntityNotFound() {
        Proveedor proveedor = new Proveedor();
        when(proveedorJpaRepository
                .existsById(Mockito.anyLong()))
                .thenReturn(false);
        Optional<Proveedor> proveedorAdapter = proveedorJpaRepositoryAdapter.update(proveedor);
        assertTrue(proveedorAdapter.isEmpty());
    }

    @Test
    void deleteByIdProveedorEntityExitoso() {
        ProveedorEntity proveedorEntity = new ProveedorEntity(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);
        Proveedor proveedor = new Proveedor(
                1L,
                "Jesus Orellana",
                "Jr. Las Palmeras 420",
                "999888777",
                1);
        when(proveedorJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(proveedorJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(proveedorEntity));
        doNothing().when(proveedorJpaRepository).deleteById(Mockito.anyLong());
        Optional<Proveedor> proveedorAdapter = proveedorJpaRepositoryAdapter.deleteById(proveedor.getId());
        assertNotNull(proveedorAdapter);
        proveedorAdapter.map(proveedorActual -> {
            assertEquals(proveedor.getId(), proveedorActual.getId());
            assertEquals(proveedor.getNombre(), proveedorActual.getNombre());
            assertEquals(proveedor.getDireccion(), proveedorActual.getDireccion());
            assertEquals(proveedor.getTelefono(), proveedorActual.getTelefono());
            assertEquals(proveedor.getEstado(), proveedorActual.getEstado());
            return proveedorActual;
        });
    }

    @Test
    void deleteByIdProveedorEntityNotFound() {
        Long id = 1L;
        when(proveedorJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Optional<Proveedor> proveedorAdapter = proveedorJpaRepositoryAdapter.deleteById(id);
        assertTrue(proveedorAdapter.isEmpty());
    }
}