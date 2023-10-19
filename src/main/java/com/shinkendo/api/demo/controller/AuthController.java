package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dto.TokenResponse;
import com.shinkendo.api.demo.dto.AuthenticationRequest;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ApiResponse<TokenResponse> register(@RequestBody AuthenticationRequest request) {
        Optional<TokenResponse> tokenResponse = authenticationService.register(request);

        // noinspection OptionalIsPresent
        if (tokenResponse.isEmpty()) {
            return new ApiResponse<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        return new ApiResponse<>(tokenResponse.get());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(authenticationService.login(request)));
    }
}
