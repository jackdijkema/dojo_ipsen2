package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.UserDTO;
import com.shinkendo.api.demo.dto.UserResponse;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDAO userDAO;

    @PatchMapping(path = {"/{id}"})
    public void editStudent(@PathVariable("id") UUID id, @RequestBody UserDTO user) {
        // TODO: Zorg er voor dat alleen een admin dit kan oproepen

        User myUser = userDAO.findById(id).orElseThrow();
        if (Optional.ofNullable(user.getUsername()).orElse(Optional.empty()).isPresent()) {
            myUser.setUsername(user.getUsername().get());
        }
        if (Optional.ofNullable(user.getRole()).orElse(Optional.empty()).isPresent()) {
            myUser.setRole(user.getRole().get());
        }
        userDAO.save(myUser);
    }

    @GetMapping
    @ResponseBody
    public ApiResponse<List<UserResponse>> getUsers() {
        List<UserResponse> res = userDAO
            .findAll()
            .stream()
            .map(s -> UserResponse
                    .builder()
                    .role(s.getRole())
                    .username(s.getUsername())
                    .id(s.getId())
                    .build()
            ).toList();

        return new ApiResponse<>(res);
    }
}


