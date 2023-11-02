package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.NoteDAO;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.NoteCreateDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.mapper.NoteMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Note;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final UserDAO userDAO;
    private final NoteDAO noteDao;
    private final NoteMapper noteMapper;

    @PostMapping()
    @ResponseBody
    private ApiResponse<Note> createNote(Principal principal, @RequestBody NoteCreateDTO noteCreateDTO) throws NotFoundException {
        User user = userDAO.loadUserByUsername(principal.getName());
        Note note = noteMapper.toEntity(noteCreateDTO, user.getId());
        return new ApiResponse<>(noteDao.save(note), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Note> getNoteById(Principal principal, @PathVariable UUID id) {
        userDAO.loadUserByUsername(principal.getName());
        Optional<Note> note = noteDao.getNoteById(id);


        return note
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseBody
    private ApiResponse<Note> editNote(@PathVariable UUID id, @RequestBody Note studentnote) {
        Optional<Note> note = noteDao.getNoteById(id);

        studentnote.setId(id);
        return new ApiResponse<>(noteDao.save(studentnote), HttpStatus.OK);
    }
}
