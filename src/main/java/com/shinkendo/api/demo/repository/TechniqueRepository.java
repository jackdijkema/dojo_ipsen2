package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.Technique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TechniqueRepository extends JpaRepository<Technique, UUID> {
}
