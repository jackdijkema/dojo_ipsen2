package com.shinkendo.api.demo.dao;


import com.shinkendo.api.demo.model.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SettingsDAO {
    @Autowired
    private SettingsDAO settingsDAO;

    public Settings save(Settings settings) {
        settings.setId(UUID.randomUUID());

        return settingsDAO.save(settings);
    }
}
