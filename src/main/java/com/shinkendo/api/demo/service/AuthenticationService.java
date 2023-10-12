package com.shinkendo.api.demo.service;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.TokenResponse;
import com.shinkendo.api.demo.dto.AuthenticationRequest;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponse register(AuthenticationRequest request) {
        Optional<User> foundUser = userDAO.findByUsername(request.getUsername());
        if (foundUser.isPresent()) {

        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userDAO.save(user);
        String token = jwtService.generateToken(user.getId());
        return new TokenResponse(token);
    }

    public TokenResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userDAO.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user.getId());
        return new TokenResponse(token);
    }
}
