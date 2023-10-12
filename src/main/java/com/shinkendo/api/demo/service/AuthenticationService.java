package com.shinkendo.api.demo.service;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.AuthenticationResponse;
import com.shinkendo.api.demo.dto.LoginRequest;
import com.shinkendo.api.demo.dto.RegisterRequest;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userDAO.save(user);
        String token = jwtService.generateToken(user.getId());
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userDAO.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user.getId());
        return new AuthenticationResponse(token);
    }
}
