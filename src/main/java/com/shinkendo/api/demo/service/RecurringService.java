package com.shinkendo.api.demo.service;

import com.shinkendo.api.demo.dao.LessonDAO;
import com.shinkendo.api.demo.dto.LessonCreateDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.mapper.LessonMapper;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.Technique;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class RecurringService {

    private final LessonMapper lessonMapper;
    private final LessonDAO lessonDAO;

    public long calculateWeeksUntil(LocalDate endOfRecurring) {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.WEEKS.between(currentDate, endOfRecurring);
    }

    public void createRecurringLesson(LessonCreateDTO lessonDTO) throws NotFoundException {
        LocalDate endOfRecurring = lessonDTO.getEndOfRecurring();
        long weeksUntil = calculateWeeksUntil(endOfRecurring);

        final int oneWeek = 7;
        LocalDate lessonDate = lessonDTO.getLessonDate();
        HashSet<Technique> techniques = lessonMapper.getTechniques(lessonDTO);

        for (int i = 0; i < weeksUntil; i++) {
            Lesson newLesson = new Lesson();
            newLesson.setName(lessonDTO.getName());
            lessonDate = lessonDate.plusDays(oneWeek);
            newLesson.setLessonDate(lessonDate);
            newLesson.setTechniques(techniques);
            lessonDAO.save(newLesson);
        }
    }
}
