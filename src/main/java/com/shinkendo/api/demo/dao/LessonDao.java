package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LessonDao extends Lesson {
    @Autowired
    private final LessonRepository lessonRepository;
    @Autowired
    private final UserDAO userDAO;

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public boolean removeUsers(UUID lessonId, List<UUID> userIds) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if (optionalLesson.isEmpty()) {
            return false;
        }

        Lesson lesson = optionalLesson.get();
        Set<User> students = lesson.getStudents();
        for (UUID userId : userIds) {
            Optional<User> optionalUser = userDAO.findById(userId);
            optionalUser.ifPresent(students::remove);
        }

        lessonRepository.save(lesson);
        return true;
    }

    public boolean addUsers(UUID lessonId, List<UUID> userIds) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if (optionalLesson.isEmpty()) {
            return false;
        }

        Lesson lesson = optionalLesson.get();
        Set<User> students = lesson.getStudents();
        for (UUID userId : userIds) {
            Optional<User> optionalUser = userDAO.findById(userId);
            optionalUser.ifPresent(students::add);
        }

        lessonRepository.save(lesson);
        return true;
    }


}
