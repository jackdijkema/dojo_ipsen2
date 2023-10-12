package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.Post;
import com.shinkendo.api.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostDAO {
    private final PostRepository postRepository;


    public Optional<Post> findById(UUID id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post insert(Post newPost) {
        return postRepository.save(newPost);
    }
}
