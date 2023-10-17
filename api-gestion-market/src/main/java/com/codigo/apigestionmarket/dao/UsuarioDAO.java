package com.codigo.apigestionmarket.dao;

import com.codigo.apigestionmarket.pojo.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuarios, Long> {
    Usuarios findByEmail(@Param("email") String email);
}
