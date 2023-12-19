package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.dto.LessonCreateDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonDAO extends Lesson {
    private final LessonRepository lessonRepository;
    private final UserDAO userDAO;

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> findById(UUID id) {
        return lessonRepository.findById(id);
    }

    public void saveLessons(List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            save(lesson);
        }
    }

    public void removeUser(UUID lessonId, UUID userId) throws NotFoundException {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if (optionalLesson.isEmpty()) throw new NotFoundException("Lesson " + lessonId + ", Not found.");

        Optional<User> user = userDAO.findById(userId);
        if (user.isEmpty()) throw new NotFoundException("User " + userId + ", Not found.");

        Lesson lesson = optionalLesson.get();
        Set<User> newStudents = lesson
                .getStudents()
                .stream()
                .filter(s -> s.getId() != userId)
                .collect(Collectors.toSet());

        lesson.setStudents(newStudents);

        lessonRepository.save(lesson);
    }

    public void addUser(UUID lessonId, UUID userId) throws NotFoundException {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if (optionalLesson.isEmpty()) throw new NotFoundException("Lesson " + lessonId + ", Not found.");

        Optional<User> user = userDAO.findById(userId);
        if (user.isEmpty()) throw new NotFoundException("User " + userId + ", Not found.");

        Lesson lesson = optionalLesson.get();
        Set<User> students = lesson.getStudents();
        students.add(user.get());
        lesson.setStudents(students);

        lessonRepository.save(lesson);
    }
}
