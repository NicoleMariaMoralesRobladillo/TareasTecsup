package com.codigo.semana7.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// @Data --> genera bucles
@Getter
@Setter
@AllArgsConstructor
public class Persona {
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;
}
