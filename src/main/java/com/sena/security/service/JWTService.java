package com.sena.security.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JWTService {

    private static final String secret_key = "JAtAr69wLeKKWP+S2ZMjNe0kIa3Pw4qu90gcg3axIyI=";

    public String getToken(UserDetails userData) {
        return getToken(new HashMap<>(), userData);
    }

    private String getToken(HashMap<String, Object> extraClaims, UserDetails userData) {
        return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userData.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
        .signWith(getKey(),SignatureAlgorithm.HS256)
        .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
