package com.codigo.semana7.persona.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

// @Data --> genera bucles
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;
}
