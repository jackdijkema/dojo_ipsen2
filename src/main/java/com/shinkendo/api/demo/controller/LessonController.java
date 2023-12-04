package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.LessonDAO;
import com.shinkendo.api.demo.dto.LessonAddUserDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.mapper.LessonMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.dto.LessonCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonDAO lessonDao;
    private final LessonMapper lessonMapper;

    @GetMapping
    public ApiResponse<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonDao.findAll();
        return new ApiResponse<>(lessons, HttpStatus.OK);
    }
    @PostMapping
    private ApiResponse<Lesson> lessonController(@RequestBody LessonCreateDTO lessonCreateDTO) {
        try {
            Lesson newLesson = lessonMapper.toEntity(lessonCreateDTO);
            return new ApiResponse<>(lessonDao.save(newLesson), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/user/{userid}")
    public ApiResponse<String> removeUseFromLesson(@PathVariable UUID id, @PathVariable UUID userid) {
        try {
            lessonDao.removeUser(id, userid);
            return new ApiResponse<>("Users removed successfully", HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ApiResponse<>("Failed to remove users", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/user")
    public ApiResponse<String> addUserToLesson(@PathVariable UUID id, @RequestBody LessonAddUserDTO lessonAddUserDTO) {
        try {
            lessonDao.addUser(id, lessonAddUserDTO.getId());
            return new ApiResponse<>("Users added successfully", HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ApiResponse<>("Failed to add users", HttpStatus.BAD_REQUEST);
        }
    }
}
