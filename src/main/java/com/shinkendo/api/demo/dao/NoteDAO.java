package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.Note;
import com.shinkendo.api.demo.repository.StudentNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NoteDAO {
    private StudentNoteRepository studentNoteRepository;

    public Note save(Note note) {
        note.setTimestamp(LocalDateTime.now());
        note.setId(UUID.randomUUID());

        return studentNoteRepository.save(note);
    }
}