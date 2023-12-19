package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.LessonCreateDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.Technique;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
    private final UserDAO userDao;
    private final TechniqueDAO techniqueDao;

    public Lesson toEntity(LessonCreateDTO lessonCreateDTO) throws NotFoundException {
        HashSet<User> usersList = new HashSet<>();
        for (UUID id : lessonCreateDTO.getStudents()) {
            Optional<User> user = userDao.findById(id);
            if (user.isEmpty()) throw new NotFoundException("User" + id + ", Not found");
            usersList.add(user.get());
        }

        HashSet<Technique> techniques = new HashSet<>();
        for (UUID id: lessonCreateDTO.getTechniques()) {
            Optional<Technique> technique = techniqueDao.findById(id);
            if (technique.isEmpty()) throw new NotFoundException("Technique" + id + ", Not found");
            techniques.add(technique.get());
        }

        return Lesson
                .builder()
                .name(lessonCreateDTO.getName())
                .students(usersList)
                .lessonDate(lessonCreateDTO.getLessonDate())
                .techniques(techniques)
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
