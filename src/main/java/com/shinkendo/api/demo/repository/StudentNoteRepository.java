package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.StudentNote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StudentNoteRepository extends JpaRepository<StudentNote, UUID> {
}
