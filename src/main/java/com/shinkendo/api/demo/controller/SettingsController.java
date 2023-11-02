package com.shinkendo.api.demo.controller;


import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.AuthRequestDTO;
import com.shinkendo.api.demo.dto.UserCreateDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.mapper.UserMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/settings")
@RequiredArgsConstructor
public class SettingsController {
    private final UserDAO userDAO;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('STUDENT')")
    @PatchMapping(path = {"/{id}"})
    public ApiResponse<UserResponseDTO> updatePasswordResponse(@PathVariable("id") UUID id, @RequestBody AuthRequestDTO newpasswordDTO) {
        Optional<User> foundUser = userDAO.findById(id);
        if(foundUser.isEmpty()) {
            return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
        }
        User user = foundUser.get();

        setPassword(user, newpasswordDTO.getPassword());

        User createdUser = userDAO.create(user);
        return new ApiResponse<>(userMapper.fromEntity(createdUser));
    }

    private void setPassword(User user, String password){
        user.setPassword(passwordEncoder.encode(password));
    }

}
