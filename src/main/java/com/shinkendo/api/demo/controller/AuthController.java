package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dto.TokenResponse;
import com.shinkendo.api.demo.dto.AuthenticationRequest;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse<TokenResponse>> register(@RequestBody AuthenticationRequest request) {
        Optional<TokenResponse> tokenResponse = authenticationService.register(request);

        // noinspection OptionalIsPresent
        if (tokenResponse.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("User already exists"));
        }

        return ResponseEntity.ok(new ApiResponse<>(tokenResponse.get()));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(authenticationService.login(request)));
    }
}
