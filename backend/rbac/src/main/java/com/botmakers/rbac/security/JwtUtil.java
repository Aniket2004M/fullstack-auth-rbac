package com.botmakers.rbac.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //JWT Operations
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private String EXPIRATION;

    //tokenMailGenerate
    public String generateToken(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).compact();
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
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

}
