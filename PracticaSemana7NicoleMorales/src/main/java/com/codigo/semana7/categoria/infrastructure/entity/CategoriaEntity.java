package com.codigo.semana7.categoria.infrastructure.entity;

import com.codigo.semana7.categoria.domain.model.Categoria;
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
@Table(name = "categorias")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private Integer estado;

    public static CategoriaEntity fromDomainModel(Categoria categoria){
        return new CategoriaEntity(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getEstado());
    }
    public Categoria toDomainModel(){
        return new Categoria(
                id,
                nombre,
                estado);
    }
}
