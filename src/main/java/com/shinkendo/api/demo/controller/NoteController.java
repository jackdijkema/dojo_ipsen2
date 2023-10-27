package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.NoteDAO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteDAO noteDao;

    @PostMapping()
    @ResponseBody
    private ApiResponse<Note> createNoteController(@RequestBody Note studentnote) {
        return new ApiResponse<>(noteDao.create(studentnote), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Note> getNoteById(@PathVariable UUID id) {
        Note note = noteDao.getNoteById(id);

        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    @ResponseBody
    private ApiResponse<Note> editNoteController(@PathVariable UUID id, @RequestBody Note studentnote){
        return new ApiResponse<>(noteDao.edit(id, studentnote), HttpStatus.OK);
    }
}
