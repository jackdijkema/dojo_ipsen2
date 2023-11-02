package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurriculumRepository extends JpaRepository<Curriculum, UUID> {

}
