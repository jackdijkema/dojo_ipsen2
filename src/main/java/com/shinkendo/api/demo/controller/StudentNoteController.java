package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.model.StudentNote;
import com.shinkendo.api.demo.service.StudentNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/notes")
public class StudentNoteController {
    @Autowired
    private StudentNoteService studentNoteService;


    @PostMapping()
    private Map<String, Object> studentNoteController(@RequestBody StudentNote studentnote) {
        studentNoteService.addNote(studentnote);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Note created successfully");
        response.put("status", HttpStatus.CREATED);
        return response;

    }



}
