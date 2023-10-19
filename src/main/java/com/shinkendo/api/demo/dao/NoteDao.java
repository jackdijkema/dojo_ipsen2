package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.StudentNote;
import com.shinkendo.api.demo.repository.StudentNoteRepository;
import com.shinkendo.api.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class NoteDao {
    @Autowired
    private StudentNoteRepository studentNoteRepository;

    public StudentNote addNote(StudentNote note){
        note.setTimestamp(LocalDateTime.now());
        note.setId(UUID.randomUUID());

        return studentNoteRepository.save(note);

    }
}
