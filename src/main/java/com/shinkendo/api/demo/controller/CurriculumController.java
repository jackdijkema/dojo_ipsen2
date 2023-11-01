package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dto.CurriculumCreateDTO;
import com.shinkendo.api.demo.mapper.CurriculumMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Curriculum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/api/v1/curriculum")
@RequiredArgsConstructor
public class CurriculumController {
    private final CurriculumMapper curriculumMapper;
    private final CurriculumDAO curriculumDAO;
    @GetMapping
    @ResponseBody
    public ApiResponse<List<Curriculum>> all() { return new ApiResponse<>(curriculumDAO.findAll()); }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ApiResponse<Curriculum> findById(@PathVariable UUID id) {
        var curriculum = curriculumDAO.findById(id);

        if (curriculum.isEmpty()) {
            return new ApiResponse<>("Curriculum not found", HttpStatus.NOT_FOUND);
        }
        return new ApiResponse<>(curriculum.get());
    }

    @PreAuthorize("hasAnyAuthority('SUPERADMIN')")
    @PostMapping
    @ResponseBody
    public ApiResponse<Curriculum> create(@RequestBody CurriculumCreateDTO createCurriculum) {
        try {
            Curriculum curriculum = curriculumMapper.toEntity(createCurriculum);
            return new ApiResponse<>(curriculumDAO.save(curriculum), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('SUPERADMIN')")
    @PatchMapping(value = "/{id}")
    @ResponseBody
    public ApiResponse<Curriculum> update(@RequestBody CurriculumCreateDTO updateCurriculum, @PathVariable UUID id) {
        try {
            Curriculum curriculum = curriculumMapper.toEntity(updateCurriculum);
            curriculum.setId(id);
            return new ApiResponse<>(curriculumDAO.save(curriculum), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
