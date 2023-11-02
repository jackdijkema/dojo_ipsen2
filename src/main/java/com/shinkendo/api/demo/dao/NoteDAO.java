package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.Note;
import com.shinkendo.api.demo.repository.StudentNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NoteDAO {

    private final StudentNoteRepository studentNoteRepository;

    public Note save(Note note) {
        return studentNoteRepository.save(note);
    }

    public Optional<Note> getNoteById(UUID id) {
        return studentNoteRepository.findById(id);
    }
}
