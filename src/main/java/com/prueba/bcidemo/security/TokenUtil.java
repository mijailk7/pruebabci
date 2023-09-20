package com.prueba.bcidemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    @Value("${jwt.secret.key}")
    private String secretkey;
    @Value("${jwt.time.expiration}")
    private String accestokenexpiration;

    public String createToken(String usuario){
        long expirationTime = Long.parseLong(accestokenexpiration);
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();

        return Jwts.builder()
            .setSubject(usuario)
            .setExpiration(expirationDate)
            .addClaims(extra)
            .signWith(getSignaturedKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String getUserFromToken(String token){
        return getClaim(token,Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignaturedKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignaturedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

            String usuario = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
        }catch (JwtException e){
         return null;
        }
    }

    public boolean isValidToken(String token){
        try{
            Jwts.parserBuilder()
                .setSigningKey(getSignaturedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
            return true;
        }catch (Exception e){

            return false;
        }
    }

    public Key getSignaturedKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
