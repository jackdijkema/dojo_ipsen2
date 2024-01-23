package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.LessonCreateDTO;
import com.shinkendo.api.demo.dto.LessonResponseDTO;
import com.shinkendo.api.demo.dto.TechniqueResponseDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.Technique;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
    private final UserDAO userDao;
    private final TechniqueDAO techniqueDao;
    private final TechniqueMapper techniqueMapper;
    private final UserMapper userMapper;

    public Lesson toEntity(LessonCreateDTO lessonCreateDTO) throws NotFoundException {
        HashSet<User> usersList = new HashSet<>();
        for (UUID id : lessonCreateDTO.getStudents()) {
            Optional<User> user = userDao.findById(id);
            if (user.isEmpty()) throw new NotFoundException("User" + id + ", Not found");
            usersList.add(user.get());
        }

        HashSet<Technique> techniques = getTechniques(lessonCreateDTO);

        User teacher = userDao.findById(lessonCreateDTO.getTeacherId()).orElseThrow(() -> new NotFoundException("Teacher not found"));

        return Lesson
                .builder()
                .students(usersList)
                .lessonDate(LocalDate.parse(lessonCreateDTO.getLessonDate()))
                .techniques(techniques)
                .note(lessonCreateDTO.getNote())
                .teacher(teacher)
                .build();
    }

    public HashSet<Technique> getTechniques(LessonCreateDTO lessonCreateDTO) throws NotFoundException {
        HashSet<Technique> techniques = new HashSet<>();
        for (UUID id : lessonCreateDTO.getTechniques()) {
            Optional<Technique> technique = techniqueDao.findById(id);
            if (technique.isEmpty()) throw new NotFoundException("Technique" + id + ", Not found");
            techniques.add(technique.get());
        }
        return techniques;
    }

    public LessonResponseDTO fromEntity(Lesson lesson) {
        Collection<TechniqueResponseDTO> techniques = lesson
                .getTechniques()
                .stream()
                .map(techniqueMapper::fromEntity)
                .collect(Collectors.toSet());


        String teacherName = "No Teacher Assigned";
        String teacherId = "No Teacher Assigned";

        Set<UserResponseDTO> students = lesson.getStudents().stream().map(userMapper::fromEntity).collect(Collectors.toSet());

        if (lesson.getTeacher() != null) {
            teacherName = lesson.getTeacher().getUsername();
            teacherId = lesson.getTeacher().getId().toString();
        }

        String note = "";
        if (lesson.getNote() != null) {
            note = lesson.getNote();
        }

        return LessonResponseDTO
                .builder()
                .id(lesson.getId().toString())
                .lessonDate(lesson.getLessonDate().toString())
                .note(note)
                .teacherName(teacherName)
                .teacherId(teacherId)
                .techniques(techniques)
                .students(students)
                .build();
    }
}
