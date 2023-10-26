package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.Note;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LessonDao extends Lesson {
    @Autowired
    private LessonRepository lessonRepository;

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }


}
