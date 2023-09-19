package com.prueba.bcidemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    @Value("${jwt.secret.key}")
    private String secretkey;
    @Value("${jwt.time.expiration}")
    private String accestokenexpiration;

    public String createToken(String nombre, String usuario){
        long expirationTime = Long.parseLong(accestokenexpiration);
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre",nombre);

        return Jwts.builder()
            .setSubject(usuario)
            .setExpiration(expirationDate)
            .addClaims(extra)
            .signWith(Keys.hmacShaKeyFor(secretkey.getBytes()))
            .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(secretkey.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretkey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

            String usuario = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
        }catch (JwtException e){
         return null;
        }
    }
}
