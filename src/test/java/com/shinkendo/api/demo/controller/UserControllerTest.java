package com.shinkendo.api.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.UserCreateDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.mapper.UserMapper;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Optional;
import java.util.UUID;
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
    private UserDAO userDAO;

    @MockBean
    private UserMapper userMapper;

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void should_succeed_if_user_is_found_by_id() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        User testUser = User.builder()
                .id(userId)
                .username("testUser123")
                .password("testPassword")
                .build();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userId);
        userResponseDTO.setUsername("testUser123");
        userResponseDTO.setRole(Role.STUDENT);
        userResponseDTO.setRankName("testrank");
        userResponseDTO.setRankId("testid");

        when(userDAO.findById(userId)).thenReturn(Optional.of(testUser));
        when(userMapper.fromEntity(any(User.class))).thenReturn(userResponseDTO);

        // Act and Assert
        mockMvc.perform(get("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.username").value("testUser123"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void should_succeed_if_user_is_not_found_by_id() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userDAO.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        mockMvc.perform(get("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found"));
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
    void should_succeed_if_user_is_not_created_because_username_is_taken() throws Exception {
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

        when(userDAO.findByUsername(any())).thenReturn(Optional.of(testUser));

        // Act
        ResultActions resultActions = mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userCreateDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("User already exists"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void should_succeed_if_user_is_not_created_because_username_is_empty() throws Exception {
        // Arrange
        User testUser = User.builder()
                .id(UUID.randomUUID())
                .password("testPassword")
                .build();

        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername("");
        userCreateDTO.setPassword("testPassword");
        userCreateDTO.setRank("testrank");
        userCreateDTO.setRole("STUDENT");

        when(userDAO.findByUsername(any())).thenReturn(Optional.empty());
        when(userMapper.toEntity(any(UserCreateDTO.class))).thenReturn(testUser);

        // Act
        ResultActions resultActions = mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userCreateDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Username or password is empty"));
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
    void should_succeed_if_user_is_not_deleted_because_user_does_not_exist() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userDAO.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        mockMvc.perform(delete("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("User " + userId + ", Not found."));
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
                .andExpect(jsonPath("$.payload.username").value("updatedUser"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void should_succeed_if_user_is_not_edited_because_user_does_not_exist() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userDAO.findById(userId)).thenReturn(Optional.empty());

        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername("updatedUser");
        userCreateDTO.setPassword("updatedPassword");

        // Act
        mockMvc.perform(put("/api/v1/user/{id}", userId)
                        .content(asJsonString(userCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SUPERADMIN"})
    void should_succeed_if_user_without_password_is_edited_successfully() throws Exception {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userDAO.findById(userId)).thenReturn(Optional.of(new User()));

        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername("updatedUser");

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
                .andExpect(jsonPath("$.payload.username").value("updatedUser"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

