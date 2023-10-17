package com.codigo.apigestionmarket.service;

import com.codigo.apigestionmarket.pojo.Usuarios;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UsuariosService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);
    ResponseEntity<String> login(Map<String, String> requestMap);
    List<Usuarios> obtenerTodosUsuarios();
}
