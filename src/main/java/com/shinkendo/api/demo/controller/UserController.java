package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.AuthResponseDTO;
import com.shinkendo.api.demo.dto.UserCreateDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.mapper.UserMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('SUPERADMIN')")
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
    public ApiResponse<AuthResponseDTO> register(@RequestBody UserCreateDTO userCreateDTO) {
        Optional<User> foundUser = userDAO.findByUsername(userCreateDTO.getUsername());
        if (foundUser.isPresent()) {
            return new ApiResponse<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.toEntity(userCreateDTO);
        if (user.getUsername() == null || user.getPassword() == null) {
            return new ApiResponse<>("Username or password is empty", HttpStatus.BAD_REQUEST);
        }

        Optional<String> tokenResponse = authenticationService.register(user.getUsername(), user.getPassword());

        if (tokenResponse.isEmpty()) {
            return new ApiResponse<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        User createdUser = userDAO.findByUsername(user.getUsername()).orElseThrow();

        User userWithoutPassword = User.builder()
                .id(createdUser.getId())
                .password(createdUser.getPassword())
                .username(createdUser.getUsername())
                .role(user.getRole())
                .rank(user.getRank())
                .build();

        userDAO.save(userWithoutPassword);


        String token = tokenResponse.get();
        return new ApiResponse<>(new AuthResponseDTO(token));
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @DeleteMapping(path = {"/{id}"})
    public ApiResponse<String> deleteUser(@PathVariable UUID id) {
        try {
            User user = userDAO.findById(id).orElseThrow(() -> new NotFoundException("User " + id + ", Not found."));
            userDAO.delete(id);
            return new ApiResponse<>("User deleted", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PutMapping(path = {"/{id}"})
    public ApiResponse<UserResponseDTO> editUser(@PathVariable("id") UUID id, @RequestBody UserCreateDTO userCreateDTO) {
        Optional<User> foundUser = userDAO.findById(id);
        if (foundUser.isEmpty()) {
            return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
        }

        User user = userMapper.toEntity(userCreateDTO);
        user.setId(id);
        System.out.println(user.getPassword());
        if (user.getPassword() == null) {
            user.setPassword(foundUser.get().getPassword());
        }

        User createdUser = userDAO.save(user);
        return new ApiResponse<>(userMapper.fromEntity(createdUser));
    }
}


