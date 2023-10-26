package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettingsRepository extends JpaRepository<Note, UUID> {
}
