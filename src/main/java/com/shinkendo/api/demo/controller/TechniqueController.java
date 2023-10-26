package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Post;
import com.shinkendo.api.demo.model.Technique;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/techniques")
@PreAuthorize("hasAuthority('SENSEI')")

public class TechniqueController {
    TechniqueDAO techniqueDAO;

    public ApiResponse<List<Technique>> findAll() {
        return new ApiResponse<>(techniqueDAO.findAll());
    }

    @GetMapping(value = "{id}")
    @ResponseBody
    public ApiResponse<Technique> findById(@PathVariable UUID id) {
        var technique = techniqueDAO.findById(id);

        // noinspection OptionalIsPresent
        if (technique.isEmpty()) {
            return new ApiResponse<>("Technique not found", HttpStatus.NOT_FOUND);
        }
        return new ApiResponse<>(technique.get());
    }


    @PreAuthorize("hasAuthority('SENSEI')")
    @PostMapping
    @ResponseBody
    public ApiResponse<Technique> insert(@RequestBody Technique technique) {
        return new ApiResponse<>(techniqueDAO.save(technique), HttpStatus.ACCEPTED);
    }
}