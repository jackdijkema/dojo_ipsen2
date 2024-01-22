package com.shinkendo.api.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinkendo.api.demo.controller.UserController;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.UserCreateDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.mapper.UserMapper;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Rank;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserDAO userDAO;

    @MockBean
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void getUsers() throws Exception {
        // Arrange
        when(userDAO.findAll()).thenReturn(Collections.emptyList());

        // Act and Assert
        mockMvc.perform(get("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void should_succeed_if_new_user_is_created() throws Exception {
        // Arrange
        User testUser = User.builder()
                .id(UUID.randomUUID())
                .username("testUser123")
                .password("testPassword")
                .build();

        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername("testUser123");
        userCreateDTO.setPassword("testPassword");
        userCreateDTO.setRank("testrank");
        userCreateDTO.setRole("STUDENT");

        when(userDAO.findByUsername(any())).thenReturn(Optional.empty())
                .thenReturn(Optional.of(testUser)); //Return empty for first call, return testUser for second call when user is created
        when(authenticationService.register(anyString(), anyString())).thenReturn(Optional.of("mocked_token"));
        when(userMapper.toEntity(any(UserCreateDTO.class))).thenReturn(testUser);

        // Act
        ResultActions resultActions = mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.token").value("mocked_token"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void should_succeed_if_user_is_deleted_successfully() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userDAO.findById(userId)).thenReturn(Optional.of(new User()));

        // Act and Assert
        mockMvc.perform(delete("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User deleted"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void should_succeed_if_user_is_edited_successfully() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userDAO.findById(userId)).thenReturn(Optional.of(new User()));

        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername("updatedUser");
        userCreateDTO.setPassword("updatedPassword");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userId);
        userResponseDTO.setUsername("updatedUser");
        userResponseDTO.setRole(Role.STUDENT);
        userResponseDTO.setRankName("testrank");
        userResponseDTO.setRankId("testid");

        User updatedUser = User.builder()
                .id(UUID.randomUUID())
                .username("updatedUser")
                .password("updatedPassword")
                .build();

        when(userMapper.toEntity(any(UserCreateDTO.class))).thenReturn(updatedUser);
        when(userDAO.save(any(User.class))).thenReturn(updatedUser);
        when(userMapper.fromEntity(any(User.class))).thenReturn(userResponseDTO);

        // Act
        mockMvc.perform(put("/api/v1/user/{id}", userId)
                        .content(asJsonString(userCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload").isNotEmpty());

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

