package com.shinkendo.api.demo.service;

import com.shinkendo.api.demo.dao.NoteDao;
import com.shinkendo.api.demo.model.StudentNote;
import com.shinkendo.api.demo.repository.StudentNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service

public class StudentNoteService  {
    @Autowired
    private NoteDao noteDao;

    public StudentNote addNote(StudentNote note){
        note.setTimestamp(LocalDateTime.now());
        note.setId(UUID.randomUUID());

        return noteDao.addNote(note);

    }


}
