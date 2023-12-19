package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.CurriculumDAO;
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
    @GetMapping
    @ResponseBody
    public ApiResponse<List<Curriculum>> all() {
        return new ApiResponse<>(curriculumDAO.findAll());
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
