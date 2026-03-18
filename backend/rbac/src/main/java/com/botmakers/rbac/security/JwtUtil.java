package com.botmakers.rbac.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //JWT Operations
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private Long EXPIRATION;

    //tokenMailGenerate
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.ES256,SECRET.getBytes())
                .compact();
    }

    //getGmailFromToken
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    //validateToken
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
    }

}
