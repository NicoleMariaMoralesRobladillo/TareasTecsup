package com.codigo.semana7.proveedor.infrastructure.entity;

import com.codigo.semana7.proveedor.domain.model.Proveedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proveedores")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String direccion;

    @Column(length = 15)
    private String telefono;

    private Integer estado;

    public static ProveedorEntity fromDomainModel(Proveedor proveedor){
        return new ProveedorEntity(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getTelefono(),
                proveedor.getEstado());
    }
    public Proveedor toDomainModel(){
        return new Proveedor(
                id,
                nombre,
                direccion,
                telefono,
                estado);
    }
}