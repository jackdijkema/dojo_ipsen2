package com.shinkendo.api.demo.controller;


import com.shinkendo.api.demo.dao.SettingsDAO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Settings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/settings")

public class SettingsController {

    SettingsDAO settingsDAO;
    @GetMapping(value = "{id}")
    @ResponseBody
    public ApiResponse<Settings> findByStudentId(@PathVariable UUID id) {
        Optional<Settings> settings = settingsDAO.findByStudentId(id);

        if (settings.isEmpty()) {
            return new ApiResponse<>("User's settings not found", HttpStatus.NOT_FOUND);
        }
        return new ApiResponse<>(settings.get());
    }

    @PostMapping
    @ResponseBody
    public ApiResponse<Settings> save(@RequestBody Settings settings) {
        return new ApiResponse<>(settingsDAO.save(settings), HttpStatus.ACCEPTED);
    }

}
