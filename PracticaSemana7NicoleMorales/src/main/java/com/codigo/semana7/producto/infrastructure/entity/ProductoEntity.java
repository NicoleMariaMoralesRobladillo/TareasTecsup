package com.codigo.semana7.producto.infrastructure.entity;

import com.codigo.semana7.categoria.infrastructure.entity.CategoriaEntity;
import com.codigo.semana7.producto.domain.model.Producto;
import com.codigo.semana7.proveedor.infrastructure.entity.ProveedorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double precio;

    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private ProveedorEntity proveedor;

    public static ProductoEntity fromDomainModel(Producto producto){
        return new ProductoEntity(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getEstado(),
                CategoriaEntity.fromDomainModel(producto.getCategoria()),
                ProveedorEntity.fromDomainModel(producto.getProveedor()));
    }
    public Producto toDomainModel(){
        return new Producto(
                id,
                nombre,
                descripcion,
                precio,
                estado,
                categoria.toDomainModel(),
                proveedor.toDomainModel());
    }
}