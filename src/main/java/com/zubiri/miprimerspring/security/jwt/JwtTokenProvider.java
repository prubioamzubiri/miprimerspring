package com.zubiri.miprimerspring.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception ex) {
            throw new RuntimeException("Unable to get username from token", ex);
        }
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException ex) {
            throw new RuntimeException("Invalid JWT signature");
        } catch (io.jsonwebtoken.ExpiredJwtException ex) {
            throw new RuntimeException("Expired JWT token");
        } catch (io.jsonwebtoken.UnsupportedJwtException ex) {
            throw new RuntimeException("Unsupported JWT token");
        } catch (io.jsonwebtoken.MalformedJwtException ex) {
            throw new RuntimeException("Malformed JWT token");
        } catch (Exception ex) {
            throw new RuntimeException("Invalid JWT token");
        }
    }
}
