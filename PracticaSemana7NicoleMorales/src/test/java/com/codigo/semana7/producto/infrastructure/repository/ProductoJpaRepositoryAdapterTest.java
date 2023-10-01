package com.codigo.semana7.producto.infrastructure.repository;

import com.codigo.semana7.categoria.domain.model.Categoria;
import com.codigo.semana7.categoria.infrastructure.entity.CategoriaEntity;
import com.codigo.semana7.producto.domain.model.Producto;
import com.codigo.semana7.producto.infrastructure.entity.ProductoEntity;
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

class ProductoJpaRepositoryAdapterTest {
    @Mock
    ProductoJpaRepository productoJpaRepository;

    @InjectMocks
    ProductoJpaRepositoryAdapter productoJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productoJpaRepositoryAdapter = new ProductoJpaRepositoryAdapter(productoJpaRepository);
    }

    @Test
    void saveProductoEntityExitoso() {
        // Lo que enviamos al método de la clase que estamos probando
        Producto producto = new Producto(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new Categoria(),
                new Proveedor());

        // Lo que enviamos a BD como simulación
        ProductoEntity productoEntity = new ProductoEntity(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new CategoriaEntity(),
                new ProveedorEntity());

        // Simulando comportamiento
        when(productoJpaRepository.save(
                Mockito.any(ProductoEntity.class))).thenReturn(productoEntity);

        Producto productoAdapter = productoJpaRepositoryAdapter.save(producto);

        assertNotNull(productoAdapter);
        assertEquals(producto.getId(), productoAdapter.getId());
        assertEquals(producto.getNombre(), productoAdapter.getNombre());
        assertEquals(producto.getDescripcion(), productoAdapter.getDescripcion());
        assertEquals(producto.getPrecio(), productoAdapter.getPrecio());
        assertEquals(producto.getEstado(), productoAdapter.getEstado());
        assertEquals(producto.getCategoria().getId(), productoAdapter.getCategoria().getId());
        assertEquals(producto.getCategoria().getNombre(), productoAdapter.getCategoria().getNombre());
        assertEquals(producto.getCategoria().getEstado(), productoAdapter.getCategoria().getEstado());
        assertEquals(producto.getProveedor().getId(), productoAdapter.getProveedor().getId());
        assertEquals(producto.getProveedor().getNombre(), productoAdapter.getProveedor().getNombre());
        assertEquals(producto.getProveedor().getDireccion(), productoAdapter.getProveedor().getDireccion());
        assertEquals(producto.getProveedor().getTelefono(), productoAdapter.getProveedor().getTelefono());
        assertEquals(producto.getProveedor().getEstado(), productoAdapter.getProveedor().getEstado());
    }

    @Test
    void findAllProductoEntityExitoso() {
        ProductoEntity productoEntity = new ProductoEntity(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new CategoriaEntity(),
                new ProveedorEntity());
        List<ProductoEntity> productoEntityList = new ArrayList<>();
        productoEntityList.add(productoEntity);
        Producto producto = new Producto(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new Categoria(),
                new Proveedor());
        List<Producto> productoList = new ArrayList<>();
        productoList.add(producto);
        when(productoJpaRepository.findAll()).thenReturn(productoEntityList);
        List<Producto> productoAdapterList = productoJpaRepositoryAdapter.findAll();
        assertNotNull(productoAdapterList);
        productoList.stream().map(
                producto1 -> {
                    assertTrue(
                            productoAdapterList.contains(new Producto(
                                    producto1.getId(),
                                    producto1.getNombre(),
                                    producto1.getDescripcion(),
                                    producto1.getPrecio(),
                                    producto1.getEstado(),
                                    new Categoria(
                                            producto1.getCategoria().getId(),
                                            producto1.getCategoria().getNombre(),
                                            producto1.getCategoria().getEstado()),
                                    new Proveedor(
                                            producto1.getProveedor().getId(),
                                            producto1.getProveedor().getNombre(),
                                            producto1.getProveedor().getDireccion(),
                                            producto1.getProveedor().getTelefono(),
                                            producto1.getProveedor().getEstado())
                            ))
                    );
                    return producto1;
                }
        );
        assertEquals(productoList.size(), productoAdapterList.size());
    }

    @Test
    void findAllProductoEntityEmptyList() {
        List<ProductoEntity> productoEntityList = new ArrayList<>();
        List<Producto> productoList = new ArrayList<>();
        when(productoJpaRepository.findAll()).thenReturn(productoEntityList);
        List<Producto> productoAdapterList = productoJpaRepositoryAdapter.findAll();
        assertNotNull(productoAdapterList);
        assertTrue(productoAdapterList.isEmpty());
        assertEquals(productoList, productoAdapterList);
    }

    @Test
    void findByIdProductoEntityExitoso() {
        ProductoEntity productoEntity = new ProductoEntity(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new CategoriaEntity(),
                new ProveedorEntity());
        Producto producto = new Producto(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new Categoria(),
                new Proveedor());
        when(productoJpaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(productoEntity));
        Optional<Producto> productoAdapter = productoJpaRepositoryAdapter.findById(producto.getId());
        assertNotNull(productoAdapter);
        productoAdapter.map(productoActual -> {
            assertEquals(producto.getId(), productoActual.getId());
            assertEquals(producto.getNombre(), productoActual.getNombre());
            assertEquals(producto.getDescripcion(), productoActual.getDescripcion());
            assertEquals(producto.getPrecio(), productoActual.getPrecio());
            assertEquals(producto.getEstado(), productoActual.getEstado());
            assertEquals(producto.getCategoria().getId(), productoActual.getCategoria().getId());
            assertEquals(producto.getCategoria().getNombre(), productoActual.getCategoria().getNombre());
            assertEquals(producto.getCategoria().getEstado(), productoActual.getCategoria().getEstado());
            assertEquals(producto.getProveedor().getId(), productoActual.getProveedor().getId());
            assertEquals(producto.getProveedor().getNombre(), productoActual.getProveedor().getNombre());
            assertEquals(producto.getProveedor().getDireccion(), productoActual.getProveedor().getDireccion());
            assertEquals(producto.getProveedor().getTelefono(), productoActual.getProveedor().getTelefono());
            assertEquals(producto.getProveedor().getEstado(), productoActual.getProveedor().getEstado());
            return productoActual;
        });
    }

    @Test
    void findByIdProductoEntityNotFound() {
        Long id = 1L;
        when(productoJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<Producto> productoAdapter = productoJpaRepositoryAdapter.findById(id);
        assertTrue(productoAdapter.isEmpty());
    }

    @Test
    void updateProductoEntityExitoso() {
        Producto producto = new Producto(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new Categoria(),
                new Proveedor());
        ProductoEntity productoEntity = new ProductoEntity(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new CategoriaEntity(),
                new ProveedorEntity());
        when(productoJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(productoJpaRepository.save(Mockito.any(ProductoEntity.class))).thenReturn(productoEntity);
        Optional<Producto> productoAdapter = productoJpaRepositoryAdapter.update(producto);
        assertNotNull(productoAdapter);
        productoAdapter.map(
                productoActual -> {
                    assertEquals(producto.getId(), productoActual.getId());
                    assertEquals(producto.getNombre(), productoActual.getNombre());
                    assertEquals(producto.getDescripcion(), productoActual.getDescripcion());
                    assertEquals(producto.getPrecio(), productoActual.getPrecio());
                    assertEquals(producto.getEstado(), productoActual.getEstado());
                    assertEquals(producto.getCategoria().getId(), productoActual.getCategoria().getId());
                    assertEquals(producto.getCategoria().getNombre(), productoActual.getCategoria().getNombre());
                    assertEquals(producto.getCategoria().getEstado(), productoActual.getCategoria().getEstado());
                    assertEquals(producto.getProveedor().getId(), productoActual.getProveedor().getId());
                    assertEquals(producto.getProveedor().getNombre(), productoActual.getProveedor().getNombre());
                    assertEquals(producto.getProveedor().getDireccion(), productoActual.getProveedor().getDireccion());
                    assertEquals(producto.getProveedor().getTelefono(), productoActual.getProveedor().getTelefono());
                    assertEquals(producto.getProveedor().getEstado(), productoActual.getProveedor().getEstado());
                    return productoActual;
                }
        );
    }

    @Test
    void updateProductoEntityNotFound() {
        Producto producto = new Producto();
        when(productoJpaRepository
                .existsById(Mockito.anyLong()))
                .thenReturn(false);
        Optional<Producto> productoAdapter = productoJpaRepositoryAdapter.update(producto);
        assertTrue(productoAdapter.isEmpty());
    }

    @Test
    void deleteByIdProductoEntityExitoso() {
        ProductoEntity productoEntity = new ProductoEntity(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new CategoriaEntity(),
                new ProveedorEntity());
        Producto producto = new Producto(
                1L,
                "Carro",
                "Carro BMW nuevo",
                7000.80,
                1,
                new Categoria(),
                new Proveedor());
        when(productoJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(productoJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(productoEntity));
        doNothing().when(productoJpaRepository).deleteById(Mockito.anyLong());
        Optional<Producto> productoAdapter = productoJpaRepositoryAdapter.deleteById(producto.getId());
        assertNotNull(productoAdapter);
        productoAdapter.map(productoActual -> {
            assertEquals(producto.getId(), productoActual.getId());
            assertEquals(producto.getNombre(), productoActual.getNombre());
            assertEquals(producto.getDescripcion(), productoActual.getDescripcion());
            assertEquals(producto.getPrecio(), productoActual.getPrecio());
            assertEquals(producto.getEstado(), productoActual.getEstado());
            assertEquals(producto.getCategoria().getId(), productoActual.getCategoria().getId());
            assertEquals(producto.getCategoria().getNombre(), productoActual.getCategoria().getNombre());
            assertEquals(producto.getCategoria().getEstado(), productoActual.getCategoria().getEstado());
            assertEquals(producto.getProveedor().getId(), productoActual.getProveedor().getId());
            assertEquals(producto.getProveedor().getNombre(), productoActual.getProveedor().getNombre());
            assertEquals(producto.getProveedor().getDireccion(), productoActual.getProveedor().getDireccion());
            assertEquals(producto.getProveedor().getTelefono(), productoActual.getProveedor().getTelefono());
            assertEquals(producto.getProveedor().getEstado(), productoActual.getProveedor().getEstado());
            return productoActual;
        });
    }

    @Test
    void deleteByIdProductoEntityNotFound() {
        Long id = 1L;
        when(productoJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Optional<Producto> productoAdapter = productoJpaRepositoryAdapter.deleteById(id);
        assertTrue(productoAdapter.isEmpty());
    }
}