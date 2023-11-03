package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dao.LessonDAO;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.NoteCreateDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.Note;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NoteMapper {
    private final UserDAO userDAO;
    private final LessonDAO lessonDAO;


    public Note toEntity(NoteCreateDTO createNoteDTO, UUID userId) throws NotFoundException {
        Optional<User> user = userDAO.findById(userId);
        if (user.isEmpty()) throw new NotFoundException("User with id: " + userId + " not found");

        Optional<Lesson> lesson = lessonDAO.findById(createNoteDTO.getLessonId());
        if (lesson.isEmpty()) throw new NotFoundException("Lesson with id: " + createNoteDTO.getLessonId() + " not found.");

        return Note.builder()
                .body(createNoteDTO.getBody())
                .user(user.get())
//                .lesson(lesson.get())
                .build();
    }
}
