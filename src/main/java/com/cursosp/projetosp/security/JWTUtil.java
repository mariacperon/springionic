package com.cursosp.projetosp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username){
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration)).signWith(SignatureAlgorithm.ES512, secret.getBytes(StandardCharsets.UTF_8)).compact();
    }
}
