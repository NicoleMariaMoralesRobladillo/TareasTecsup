package com.codigo.apigestionmarket.security.jwt;

import com.codigo.apigestionmarket.constantes.Constantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private String tokenSecret = Constantes.TOKEN_SECRET;

    public Claims extractAllClaims(String tokenSecret) {
        return Jwts.parser().setSigningKey(this.tokenSecret).parseClaimsJws(tokenSecret).getBody();
    }

    public <T> T extractClaims(String tokenSecret, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(tokenSecret);
        return claimsResolver.apply(claims);
    }

    public Date extractExpiration(String tokenSecret) {
        return extractClaims(tokenSecret, Claims::getExpiration);
    }

    public String extractUserName(String tokenSecret) {
        return extractClaims(tokenSecret, Claims::getSubject);
    }

    public Boolean isTokenExpired(String tokenSecret) {
        return extractExpiration(tokenSecret).before(new Date());
    }

    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100*60*60*10))
                .signWith(SignatureAlgorithm.HS384, tokenSecret)
                .compact();
    }

    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }

    public Boolean validateToken(String tokenSecret,
                                 UserDetails userDetails) {
        final String username = extractUserName(tokenSecret);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(tokenSecret));
    }
}
