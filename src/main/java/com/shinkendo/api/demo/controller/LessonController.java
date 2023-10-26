package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.LessonDao;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.LessonCreationRequest;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    private ApiResponse<Lesson> lessonController(@RequestBody LessonCreationRequest request) {
        List<User> usersList = new ArrayList<>();
        for (UUID i : request.getUsers()) {
            usersList.add(userDAO.findById(i).get());
        }

        Lesson newLesson = new Lesson();
        newLesson.setName(request.getName());  // Set the name here
        newLesson.setStudents(new HashSet<>(usersList));

        return new ApiResponse<>(lessonDao.save(newLesson), HttpStatus.OK);
    }


    @DeleteMapping("/remove/{id}")
    public ApiResponse<String> removeUsersFromLesson(@PathVariable UUID id, @RequestBody List<UUID> users) {
        boolean success = lessonDao.removeUsers(id, users);
        if (success) {
            return new ApiResponse<>("Users removed successfully", HttpStatus.OK);
        }
        else {
            return new ApiResponse<>("Failed to remove users", HttpStatus.BAD_REQUEST);
        }
    }

    // Voegt een user toe aan een al bestaande les
    @PostMapping("/add/{id}")
    public ApiResponse<String> addUsersToLesson(@PathVariable UUID id, @RequestBody List<UUID> users) {
        boolean success = lessonDao.addUsers(id, users);
        if (success) {
            return new ApiResponse<>("Users added successfully", HttpStatus.OK);
        } else {
            return new ApiResponse<>("Failed to add users", HttpStatus.BAD_REQUEST);
        }
    }
}
