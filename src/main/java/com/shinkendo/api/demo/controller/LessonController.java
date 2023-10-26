package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.LessonDao;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonDao lessonDao;
    private final UserDAO userDAO;

    @PostMapping
    private ApiResponse<Lesson> lessonController(@RequestBody List<UUID> users) {
        List<User> usersList =  new ArrayList<>();
        for (UUID i : users) {
            usersList.add(userDAO.findById(i).get());
        }

        Lesson newLesson = new Lesson();
        newLesson.setStudents(new HashSet<>(usersList));
        // Set other fields for newLesson

        return new ApiResponse<>(lessonDao.save(newLesson), HttpStatus.OK);
    }
}
