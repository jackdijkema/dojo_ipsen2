package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.PostDAO;
import com.shinkendo.api.demo.dto.PostCreateDTO;
import com.shinkendo.api.demo.mapper.PostMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostDAO postDAO;
    private final PostMapper postMapper;

    @GetMapping
    @ResponseBody
    public ApiResponse<List<Post>> all() {
        return new ApiResponse<>(postDAO.findAll());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ApiResponse<Post> findById(@PathVariable UUID id) {
        var post = postDAO.findById(id);

        return post
                .map(ApiResponse::new)
                .orElseGet(() ->
                        new ApiResponse<>("Post not found", HttpStatus.NOT_FOUND)
                );
    }

    @PreAuthorize("hasAuthority('SENSEI')")
    @PatchMapping(value = "/{id}")
    @ResponseBody
    public ApiResponse<Post> update(@PathVariable UUID id, @RequestBody PostCreateDTO post) {
        var foundPost = postDAO.findById(id);

        if (foundPost.isEmpty()) {
            return new ApiResponse<>("Post not found", HttpStatus.NOT_FOUND);
        }

        var postToUpdate = foundPost.get();
        postToUpdate.setName(post.getName());
        postToUpdate.setBody(post.getBody());
        postToUpdate.setId(id);

        return new ApiResponse<>(postDAO.save(postToUpdate));
    }

    @PreAuthorize("hasAuthority('SENSEI')")
    @PostMapping
    @ResponseBody
    public ApiResponse<Post> insert(@RequestBody PostCreateDTO newPost) {
        Post post = postMapper.toEntity(newPost);
        return new ApiResponse<>(postDAO.save(post), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('SENSEI')")
    @DeleteMapping(value = "/{id}")
    public ApiResponse<String> deletePost(@PathVariable UUID id) {
        try {
            Post post = postDAO.findById(id).orElseThrow();
            postDAO.delete(post.getId());
            return new ApiResponse<>("Post deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ApiResponse<>("Post not found", HttpStatus.NOT_FOUND);
        }
    }
}
