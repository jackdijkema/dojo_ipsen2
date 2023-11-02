package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dto.AuthRequestDTO;
import com.shinkendo.api.demo.dto.AuthResponseDTO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PostMapping(value = "/register")
    public ApiResponse<AuthResponseDTO> register(@RequestBody AuthRequestDTO loginDTO) {
        Optional<String> tokenResponse = authenticationService.register(loginDTO.getUsername(), loginDTO.getPassword());

        if (tokenResponse.isEmpty()) {
            return new ApiResponse<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        String token = tokenResponse.get();
        return new ApiResponse<>(new AuthResponseDTO(token));
    }

    @PostMapping(value = "/login")
    public ApiResponse<AuthResponseDTO> login(@RequestBody AuthRequestDTO loginDTO) {
        String token = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return new ApiResponse<>(new AuthResponseDTO(token));
    }
}
