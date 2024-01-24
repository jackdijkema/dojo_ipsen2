package com.shinkendo.api.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinkendo.api.demo.dao.PostDAO;
import com.shinkendo.api.demo.dto.PostCreateDTO;
import com.shinkendo.api.demo.mapper.PostMapper;
import com.shinkendo.api.demo.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostDAO postDAO;

    @Mock
    private PostMapper postMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testCreatePost() throws Exception {
        PostCreateDTO postCreateDTO = new PostCreateDTO();
        postCreateDTO.setName("Test Name");
        postCreateDTO.setBody("Test Body");

        Post post = new Post();
        post.setId(UUID.randomUUID());
        post.setName(postCreateDTO.getName());
        post.setBody(postCreateDTO.getBody());

        when(postMapper.toEntity(any(PostCreateDTO.class))).thenReturn(post);
        when(postDAO.save(any(Post.class))).thenReturn(post);

        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postCreateDTO)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testUpdate() throws Exception {
        UUID id = UUID.randomUUID();

        Post post = new Post();
        post.setId(id);
        post.setName("Initial Name");
        post.setBody("Initial Body");

        when(postDAO.findById(any(UUID.class))).thenReturn(Optional.of(post));

        PostCreateDTO postUpdate = new PostCreateDTO();
        postUpdate.setName("Updated Name");
        postUpdate.setBody("Updated Body");

        mockMvc.perform(patch("/api/v1/posts/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postUpdate)))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(authorities = "SUPERADMIN")
    public void testDeletePost() throws Exception {
        // Given
        UUID id = UUID.randomUUID();
        Post post = new Post();
        post.setId(id);

        // Mocking the behavior of postDAO
        when(postDAO.findById(any(UUID.class))).thenReturn(Optional.of(post));

        // Performing the DELETE request
        mockMvc.perform(delete("/api/v1/posts/" + id))
                .andExpect(status().isOk());

        // Verifying that postDAO.delete() was called with the correct ID
        verify(postDAO, times(1)).delete(id);
    }

    @Test
    @WithMockUser(authorities = "SUPERADMIN")
    public void testDeletePostNotFound() throws Exception {
        // Given
        UUID id = UUID.randomUUID();

        // Mocking the behavior of postDAO when post is not found
        when(postDAO.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Performing the DELETE request
        mockMvc.perform(delete("/api/v1/posts/" + id))
                .andExpect(status().isNotFound());
    }
}