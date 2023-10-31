package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.NoteDAO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteDAO noteDao;

    @PostMapping()
    private ApiResponse<Note> studentNoteController(@RequestBody Note studentnote) {
        return new ApiResponse<>(noteDao.save(studentnote), HttpStatus.OK);
    }
}
