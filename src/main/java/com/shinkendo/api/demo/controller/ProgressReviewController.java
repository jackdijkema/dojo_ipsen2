package com.shinkendo.api.demo.controller;


import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.ProgressResponseDTO;
import com.shinkendo.api.demo.mapper.ProgressReviewMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/progress")
@RequiredArgsConstructor
public class ProgressReviewController {
    private final ProgressReviewMapper progressReviewMapper;
    private final UserDAO userDAO;

    @GetMapping(value = "{id}")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('SENSEI')")
    public ApiResponse<ProgressResponseDTO> findById(@PathVariable UUID id) {
        Optional<User> user = userDAO.findById(id);
        //noinspection OptionalIsPresent
        if (user.isEmpty()) {
            return new ApiResponse<>("User not found", HttpStatus.NOT_FOUND);
        }

        return new ApiResponse<>(progressReviewMapper.fromEntity(user.get()), HttpStatus.OK);
    }
}
