package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {  }
