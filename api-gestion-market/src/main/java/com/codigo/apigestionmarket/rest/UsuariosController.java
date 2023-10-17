package com.codigo.apigestionmarket.rest;

import com.codigo.apigestionmarket.constantes.Constantes;
import com.codigo.apigestionmarket.pojo.Usuarios;
import com.codigo.apigestionmarket.service.UsuariosService;
import com.codigo.apigestionmarket.util.MarketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/signup")
    public ResponseEntity<String> registrarUsuario(
            @RequestBody(required = true) Map<String, String> requestMap) {
        try {
            return usuariosService.signUp(requestMap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return MarketUtils.getResponseEntity(Constantes.ALGO_SALIO_MAL,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody(required = true) Map<String, String> requestMap) {
        try {
            return usuariosService.login(requestMap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return MarketUtils.getResponseEntity(Constantes.ALGO_SALIO_MAL,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/todos")
    public List<Usuarios> todosUsuarios() {
        try {
            return usuariosService.obtenerTodosUsuarios();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
