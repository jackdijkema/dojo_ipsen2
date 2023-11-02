package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.ProgressReviewDAO;
import com.shinkendo.api.demo.dto.ProgressReviewDTO;
import com.shinkendo.api.demo.mapper.ProgressReviewMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.ProgressReview;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/progress")
@PreAuthorize("hasAuthority('SENSEI')")
@RequiredArgsConstructor
public class ProgressReviewController {

    private final ProgressReviewDAO progressReviewDAO;
    private final ProgressReviewMapper progressReviewMapper;

    @GetMapping(value = "{id}")
    @ResponseBody
    public ApiResponse<ProgressReview> findById(@PathVariable UUID id) {
        Optional<ProgressReview> progressReview = progressReviewDAO.findById(id);

        if (progressReview.isEmpty()) {
            return new ApiResponse<>("Progress review not found", HttpStatus.NOT_FOUND);
        }
        return new ApiResponse<>(progressReview.get());
    }


    @PreAuthorize("hasAnyAuthority('SUPERADMIN')")
    @PostMapping
    @ResponseBody
    public ApiResponse<ProgressReview> create(@RequestBody ProgressReviewDTO progressReviewDTO) {
        try {
            ProgressReview progressReview = progressReviewMapper.toEntity(progressReviewDTO);
            return new ApiResponse<>(progressReviewDAO.create(progressReview), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ApiResponse<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
