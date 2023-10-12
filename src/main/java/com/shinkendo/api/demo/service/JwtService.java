package com.shinkendo.api.demo.service;

import com.shinkendo.api.demo.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "zNv0GDzxDcPqQSPd7wMfZkuHD6RxIiAOvbp6+JQMsUWC/QgmFm90mEFw1xhYX3vxbvzvRMOnUfLSkL+iU6eLeTQMYWuXnpyT4hUNIaOUW4wzmn1A5EZoCMrFiVEweAvEtoCawF9gXyAjDqyXa8kyIQ0MRc5ArzRHC9toEeFpYW4iOA6ZOMURB/m6wytUUlI+5UfCPgl/ZMNi7gM9b3v3SN1kla+Ra50I69f5p3YyZ3jmwOxlr/pMdyl0VRZPePZ3uA6N8XOdtws12Jmw/bQAJgB6xQEtHONDqrlYyRoDU4NgcJDsE+5xVygcPAzhQnOzOpVNSuOsZMKvnTQiPwyWNg==";

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UUID userId) {
        return generateToken(Map.of(), userId);
    }

    public String generateToken(Map<String, Object> extraClaims, UUID userId) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UUID userID) {
        final String userId = extractUserId(token);
        return userId.equals(userID.toString()) && !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
