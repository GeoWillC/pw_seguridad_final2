package com.example.demo.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwUtils
 */
@Component
public class JwtUtils {

    @Value("${app.jwtSemilla}")
    private String jwtSemilla;
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @SuppressWarnings("deprecation")
	public String buildTokenJwt(String nombre) {
        // Expira luego de un tiempo
    	 return Jwts.builder()
    	            .setSubject(nombre)
    	            .setIssuedAt(new Date())
    	            .setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationMs))
    	            .signWith(SignatureAlgorithm.HS256, this.jwtSemilla)
    	            .compact();
    }
    
}