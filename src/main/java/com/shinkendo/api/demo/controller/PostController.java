package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.PostDAO;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostDAO postDAO;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<ApiResponse<List<Post>>> all() {
        return new ResponseEntity<>(new ApiResponse<>(postDAO.findAll()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponse<Post>> findById(@PathVariable UUID id) {
        var post = postDAO.findById(id);

        // noinspection OptionalIsPresent
        if (post.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>("Post not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(post.get()), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<ApiResponse<Post>> insert(@RequestBody Post newPost) {
        return new ResponseEntity<>(new ApiResponse<>(postDAO.insert(newPost)), HttpStatus.ACCEPTED);
    }

}
