package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dto.CurriculumResponseDTO;
import com.shinkendo.api.demo.mapper.CurriculumMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Curriculum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(value = "/api/v1/curriculum")
@RequiredArgsConstructor
public class CurriculumController {
    private final CurriculumDAO curriculumDAO;
    private final CurriculumMapper curriculumMapper;

    @GetMapping
    @ResponseBody
    public ApiResponse<List<CurriculumResponseDTO>> all() {

        List<CurriculumResponseDTO> curriculumResponseDTOS = curriculumDAO
                .findAll()
                .stream()
                .map(curriculumMapper::fromEntity)
                .toList();

        return new ApiResponse<>(curriculumResponseDTOS);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ApiResponse<CurriculumResponseDTO> findById(@PathVariable UUID id) {
        Optional<Curriculum> curriculum = curriculumDAO.findById(id);

        return curriculum
                .map(value -> new ApiResponse<>(curriculumMapper.fromEntity(value)))
                .orElseGet(() -> new ApiResponse<>("Curriculum not found", HttpStatus.NOT_FOUND));

    }
}
