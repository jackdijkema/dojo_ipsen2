package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.LessonCreateDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
    private final UserDAO userDao;

    public Lesson toEntity(LessonCreateDTO lessonCreateDTO) throws NotFoundException {
        HashSet<User> usersList = new HashSet<>();
        for (UUID id : lessonCreateDTO.getStudents()) {
            Optional<User> user = userDao.findById(id);
            if (user.isEmpty()) throw new NotFoundException("User" + id + ", Not found");
            usersList.add(user.get());
        }

        return Lesson
                .builder()
                .name(lessonCreateDTO.getName())
                .students(usersList)
                .lessonDate(lessonCreateDTO.getLessonDate())
                .build();
    }

    public LessonCreateDTO fromLesson(Lesson lesson) {
        List<UUID> studentIds = new ArrayList<>();
        if (lesson.getStudents() != null) {
            studentIds = lesson.getStudents().stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
        }

        return LessonCreateDTO
                .builder()
                .name(lesson.getName())
                .students(studentIds)
                .lessonDate(lesson.getLessonDate())
                .build();
    }
}
