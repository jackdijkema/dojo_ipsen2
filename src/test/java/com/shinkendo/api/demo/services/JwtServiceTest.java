package com.shinkendo.api.demo.services;

import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@SpringBootConfiguration
public class JwtServiceTest {
    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        this.jwtService = new JwtService("superlongsecretthatisatleast256bitslongerthanzero");
    }

    @Test
    public void should_create_user_token() {
        UUID userId = UUID.randomUUID();
        String token = this.jwtService.generateToken(userId);
        System.out.println(token);
        assertNotNull(token);
    }

    @Test
    public void should_read_userId_from_new_token() {
        UUID userId = UUID.randomUUID();
        String token = this.jwtService.generateToken(userId);

        UUID newId = UUID.fromString(this.jwtService.extractUserId(token));

        assertEquals(userId, newId);
    }

    @Test
    public void should_read_student_role_from_new_token() {
        UUID userId = UUID.randomUUID();
        Role userRole = Role.STUDENT;

        String token = this.jwtService.generateToken(Map.of("role", userRole), userId);

        String roleName = this.jwtService.extractClaim(token, (Claims claims) -> claims.get("role", String.class));
        Role role = Role.valueOf(roleName);

        assertEquals(userRole, role);
    }

    @Test
    public void should_be_invalid_token() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzZGY5NWFmNS1lOTA2LTQwYWQtYjg3My03ZmI4NmEyMzI5YmMiLCJpYXQiOjE3MDUxMTE3ODQsImV4cCI6MTcwNTEyODE4NH0.jk-krV_aUCgHtFzLShbGH9OPphaSP38hIQSr_-WThCA";

        assertThrows(SignatureException.class, () ->
                jwtService.extractUserId(token)
        );
    }
}
