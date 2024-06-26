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
    public Post save(Post newPost) {
        return postRepository.save(newPost);
    }

    public void delete (UUID id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) return;
        Post postToDelete = optionalPost.get();
        postRepository.delete(postToDelete);
    }
}
