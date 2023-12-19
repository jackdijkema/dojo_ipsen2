package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.LessonDAO;
import com.shinkendo.api.demo.dto.LessonAddUserDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.mapper.LessonMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.dto.LessonCreateDTO;
import com.shinkendo.api.demo.service.RecurringService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ComponentScan
@RestController
@RequestMapping(value = "/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonDAO lessonDao;
    private final LessonMapper lessonMapper;
    RecurringService lessonService = new RecurringService();

    @GetMapping
    public ApiResponse<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonDao.findAll();
        return new ApiResponse<>(lessons, HttpStatus.OK);
    }

    @PostMapping
    private ApiResponse<Lesson> lessonController(@RequestBody LessonCreateDTO lessonCreateDTO) {
        try {

            LocalDate endOfRecurring = lessonCreateDTO.getEndOfRecurring();

            if(endOfRecurring != null) {
                lessonDao.saveLessons(lessonService.createRecurringLesson(lessonCreateDTO));
                return new ApiResponse<>("Created recurring lesson succesfully...", HttpStatus.OK);
            }

            Lesson newLesson = lessonMapper.toEntity(lessonCreateDTO);

            return new ApiResponse<>(lessonDao.save(newLesson), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<LessonCreateDTO> getLessonById(@PathVariable UUID id) {
        try {
            Optional<Lesson> lessonOption = lessonDao.findById(id);
            if (lessonOption.isEmpty()) {
                throw new NotFoundException("Lesson " + id + ", Not found.");
            }

            Lesson lesson = new Lesson();
            lesson.setName(lessonOption.get().getName());
            lesson.setLessonDate(lessonOption.get().getLessonDate());
            lesson.setStudents(lessonOption.get().getStudents());

            return new ApiResponse<>(this.lessonMapper.fromLesson(lesson), HttpStatus.OK);
        }
        catch (NotFoundException e) {
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

    @PostMapping("/recurring")
    public  ApiResponse<String> createRecurringLesson(@RequestBody LessonCreateDTO lessonCreateDTO) {

        return new ApiResponse<>("Recurring lesson(s) added successfully", HttpStatus.OK);
    }
}