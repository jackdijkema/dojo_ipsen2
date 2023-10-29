package com.shinkendo.api.demo.dao;


import com.shinkendo.api.demo.model.Settings;
import com.shinkendo.api.demo.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SettingsDAO {

    private SettingsRepository settingsRepository;


    public Optional<Settings> findByStudentId(UUID id){
        return settingsRepository.findById(id);
    }

    public Settings save(Settings settings){
        return settingsRepository.save(settings);
    }


}
