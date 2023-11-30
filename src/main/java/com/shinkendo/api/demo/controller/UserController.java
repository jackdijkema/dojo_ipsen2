package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.AuthRequestDTO;
import com.shinkendo.api.demo.dto.AuthResponseDTO;
import com.shinkendo.api.demo.dto.UserCreateDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.mapper.UserMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDAO userDAO;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;

    @GetMapping
    @ResponseBody
    public ApiResponse<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> res = userDAO
            .findAll()
            .stream()
            .map(userMapper::fromEntity)
            .toList();

        return new ApiResponse<>(res);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PostMapping
    public ApiResponse<AuthResponseDTO> register(@RequestBody AuthRequestDTO loginDTO) {
        Optional<String> tokenResponse = authenticationService.register(loginDTO.getUsername(), loginDTO.getPassword());

        if (tokenResponse.isEmpty()) {
            return new ApiResponse<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        String token = tokenResponse.get();
        return new ApiResponse<>(new AuthResponseDTO(token));
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PutMapping(path = {"/{id}"})
    public ApiResponse<UserResponseDTO> editUser(@PathVariable("id") UUID id, @RequestBody UserCreateDTO userCreateDTO) {
        Optional<User> foundUser = userDAO.findById(id);
        if(foundUser.isEmpty()) {
            return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
        }

        User user = foundUser.get();

        if (userCreateDTO.getUsername() != null) {
            user.setUsername(userCreateDTO.getUsername());
        }

        if (userCreateDTO.getRole() != null) {
            user.setRole(userCreateDTO.getRole());
        }

        User createdUser = userDAO.save(user);
        return new ApiResponse<>(userMapper.fromEntity(createdUser));
    }
}


