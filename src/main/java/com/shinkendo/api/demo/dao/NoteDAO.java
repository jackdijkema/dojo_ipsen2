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

    public Note create(Note note) {
        note.setTimestamp(LocalDateTime.now());
        note.setId(UUID.randomUUID());
        note.setNoteContent(note.getNoteContent());

        return studentNoteRepository.save(note);
    }

    public Note getNoteById(UUID id) {
        return studentNoteRepository.findById(id).orElse(null);
    }

    public Note edit(UUID id, Note edit) {
        Note existingNote = studentNoteRepository.findById(id).orElse(null);
        if (existingNote != null) {
            existingNote.setTimestamp(edit.getTimestamp());
            existingNote.setNoteContent(existingNote.getNoteContent());

            return studentNoteRepository.save(existingNote);
        }
        else{
            return null;
    }
}



}
