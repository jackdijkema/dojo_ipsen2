package com.shinkendo.api.demo.service;

import com.shinkendo.api.demo.dto.LessonCreateDTO;
import com.shinkendo.api.demo.model.Lesson;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class LessonService {

    public long calculateWeeksUntil(LocalDate endOfRecurring) {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.WEEKS.between(currentDate, endOfRecurring);
    }

    public ArrayList<Lesson> createRecurringLesson(LessonCreateDTO lessonDTO) {

        LocalDate endOfRecurring = lessonDTO.getEndOfRecurring();
        long weeksUntil = calculateWeeksUntil(endOfRecurring);

        final int oneWeek = 7;
        System.out.println(weeksUntil);
        LocalDate lessonDate = lessonDTO.getLessonDate();
        System.out.println("Lesson Date: " + lessonDTO.getLessonDate());
        ArrayList<Lesson> lessonList = new ArrayList<>();

        for (int i = 0; i < weeksUntil; i++) {
            Lesson newLesson = new Lesson();
            newLesson.setName(lessonDTO.getName());

            lessonDate = lessonDate.plusDays(oneWeek);
            lessonDTO.setLessonDate(lessonDate);
            newLesson.setLessonDate(lessonDate);
            System.out.println("Lesson Date loop: " + lessonDTO.getLessonDate());
            lessonList.add(newLesson);
        }
        return lessonList;
    }
}
