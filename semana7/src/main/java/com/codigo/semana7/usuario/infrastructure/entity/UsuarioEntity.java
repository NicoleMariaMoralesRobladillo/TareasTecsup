package com.codigo.semana7.usuario.infrastructure.entity;

import com.codigo.semana7.persona.domain.model.Persona;
import com.codigo.semana7.persona.infrastructure.entity.PersonaEntity;
import com.codigo.semana7.usuario.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreUsuario;
    private String contrasenia;
    private String correoElectronico;
    @ManyToOne
    @JoinColumn(name = "id_persona")
    private PersonaEntity persona;

    public static UsuarioEntity fromDomainModel(Usuario usuario){
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getContrasenia(),
                usuario.getCorreoElectronico(),
                PersonaEntity.fromDomainModel(usuario.getPersona()));
    }
    public Usuario toDomainModel(){
        return new Usuario(
                id,
                nombreUsuario,
                contrasenia,
                correoElectronico,
                persona.toDomainModel());
    }
}
