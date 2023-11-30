package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dto.PostCreateDTO;
import com.shinkendo.api.demo.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    public Post toEntity(PostCreateDTO newPost) {
        return Post.builder()
                .name(newPost.getName())
                .body(newPost.getBody())
                .build();
    }
}
