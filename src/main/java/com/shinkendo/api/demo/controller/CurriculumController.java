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

        List<CurriculumResponseDTO> curriculumResponseDTOS = curriculumDAO.findAll().stream().map(curriculumMapper::fromEntity).toList();
        return new ApiResponse<>(curriculumResponseDTOS);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ApiResponse<Curriculum> findById(@PathVariable UUID id) {
        var curriculum = curriculumDAO.findById(id);

        //noinspection OptionalIsPresent
        if (curriculum.isEmpty()) {
            return new ApiResponse<>("Curriculum not found", HttpStatus.NOT_FOUND);
        }
        return new ApiResponse<>(curriculum.get());
    }
}
