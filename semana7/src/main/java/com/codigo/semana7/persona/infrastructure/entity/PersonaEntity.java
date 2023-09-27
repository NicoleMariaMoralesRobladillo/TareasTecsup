package com.codigo.semana7.persona.infrastructure.entity;

import com.codigo.semana7.persona.domain.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personas")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;

    public static PersonaEntity fromDomainModel(Persona persona){
        return new PersonaEntity(
                persona.getId(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getFechaNacimiento(),
                persona.getGenero());
    }
    public Persona toDomainModel(){
        return new Persona(
                id,
                nombre,
                apellido,
                fechaNacimiento,
                genero);
    }
}
