package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dto.PostCreateDTO;
import com.shinkendo.api.demo.model.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final ModelMapper modelMapper;

    public Post toEntity(PostCreateDTO newPost) {
        return modelMapper.map(newPost, Post.class);
    }
}
