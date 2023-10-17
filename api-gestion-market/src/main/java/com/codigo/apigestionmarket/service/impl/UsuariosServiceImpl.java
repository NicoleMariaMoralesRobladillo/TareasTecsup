package com.codigo.apigestionmarket.service.impl;

import com.codigo.apigestionmarket.constantes.Constantes;
import com.codigo.apigestionmarket.dao.UsuarioDAO;
import com.codigo.apigestionmarket.pojo.Usuarios;
import com.codigo.apigestionmarket.security.CustomerDetailsService;
import com.codigo.apigestionmarket.security.jwt.JwtUtil;
import com.codigo.apigestionmarket.service.UsuariosService;
import com.codigo.apigestionmarket.util.MarketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UsuariosServiceImpl implements UsuariosService {
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerDetailsService customerDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Ingreso al SignUp : " + requestMap);
        try {
            if (Boolean.TRUE.equals(validateSignUp(requestMap))) {
                Usuarios usuarios = usuarioDAO.findByEmail(requestMap.get("email"));
                if (Objects.isNull(usuarios)) {
                    usuarioDAO.save(getUsuariosMap(requestMap));
                    return MarketUtils.getResponseEntity(Constantes.MSG_USUARIO_CREADO,
                            HttpStatus.CREATED);
                }
                else {
                    return MarketUtils.getResponseEntity(Constantes.MSG_USUARIO_YA_CREADO,
                            HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return MarketUtils.getResponseEntity(Constantes.DATA_INVALIDA,
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MarketUtils.getResponseEntity(Constantes.ALGO_SALIO_MAL,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Ingreso login: " + requestMap);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"),
                            requestMap.get("password")));
            if (authentication.isAuthenticated()) {
                if (customerDetailsService.getUserDetail().getStatus() == 1) {
                    return new ResponseEntity<>("{\"token \":\""
                            + jwtUtil.generateToken(
                                    customerDetailsService.getUserDetail().getEmail(),
                            customerDetailsService.getUserDetail().getRole()) + "\"}", HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("{\"mensaje\": " + "Espera la Aprobación del administrador" + "\"}", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return new ResponseEntity<>("{\"mensaje\": " + "Credenciales incorrectas" + "\"}", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @Override
    public List<Usuarios> obtenerTodosUsuarios() {
        return usuarioDAO.findAll();
    }

    private Boolean validateSignUp(Map<String, String> requestMap) {
        return requestMap.containsKey("nombre")
                && requestMap.containsKey("numeroContacto")
                && requestMap.containsKey("email")
                && requestMap.containsKey("password");
    }

    private Usuarios getUsuariosMap(Map<String, String> requestMap) {
        Usuarios usuarios = new Usuarios();
        usuarios.setNombre(requestMap.get("nombre"));
        usuarios.setNumeroContacto(requestMap.get("numeroContacto"));
        usuarios.setEmail(requestMap.get("email"));
        usuarios.setPassword(requestMap.get("password"));
        usuarios.setStatus(Constantes.ACTIVO);
        usuarios.setRole(Constantes.ROLE_USUARIO);
        return usuarios;
    }
}
