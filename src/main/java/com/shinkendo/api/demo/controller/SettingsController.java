package com.shinkendo.api.demo.controller;


import com.shinkendo.api.demo.dao.SettingsDAO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Settings;
import com.shinkendo.api.demo.model.Technique;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/settings")

public class SettingsController {

    SettingsDAO settingsDAO;


}
