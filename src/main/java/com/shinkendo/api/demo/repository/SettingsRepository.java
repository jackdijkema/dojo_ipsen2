package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettingsRepository extends JpaRepository<Settings, UUID> {
}
