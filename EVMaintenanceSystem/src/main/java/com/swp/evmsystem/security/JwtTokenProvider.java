package com.swp.evmsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    private final Key key;

    public JwtTokenProvider() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        System.out.println("Generated JWT Key: " + key.getEncoded());//
    }

    public String generateToken(UserEntityDetails userDetails) {
        // 1 day in milliseconds
        long expiration = 86400000;
        String userId = String.valueOf(userDetails.getId());
        return Jwts.builder()
                .setSubject(userId) // Set user ID as subject
                .claim("roles", userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)   // get "ROLE_ADMIN" or "ROLE_USER"
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //set token validity to 1 day
                .signWith(key)
                .compact();
    }

    // Parse token
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 🔹 Lấy username từ token
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 🔹 Lấy roles từ token
    public List<String> getRoles(String token) {
        return getClaims(token).get("roles", List.class);
    }
}