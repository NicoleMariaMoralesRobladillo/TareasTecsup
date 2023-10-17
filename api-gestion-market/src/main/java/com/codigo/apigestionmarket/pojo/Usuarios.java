package com.codigo.apigestionmarket.pojo;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name = "Usuarios.findByEmail", query = "select u from Usuarios u where u.email=:email")
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    private String email;
    private String nombre;
    private String numeroContacto;
    private String password;
    private String role;
    private int status;
}
