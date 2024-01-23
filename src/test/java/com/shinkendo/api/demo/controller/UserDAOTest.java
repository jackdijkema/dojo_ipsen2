package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDAOTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDAO userDAO;

    @Test
    public void shoud_succeed_if_user_is_deleted_and_user_exists() {
        // Arrange
        UUID id = UUID.randomUUID();
        User expectedUser = new User();
        expectedUser.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));

        // Act
        userDAO.delete(id);

        // Assert
        verify(userRepository, times(1)).delete(expectedUser);
    }

    @Test
    public void shoud_succeed_if_user_is_not_deleted_if_it_doesnt_exists() {
        // Arrange
        UUID id = UUID.randomUUID();
        User expectedUser = new User();
        expectedUser.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        userDAO.delete(id);

        // Assert
        verify(userRepository, times(0)).delete(expectedUser);
    }

    @Test
    public void should_succeed_if_user_is_found() {
        // Arrange
        String username = "testuser";
        User expectedUser = new User();
        expectedUser.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(expectedUser));

        // Act
        userDAO.findByUsername(username);

        // Assert
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void shoud_succeed_if_user_was_not_found() {
        // Arrange
        String username = "testuser";
        User expectedUser = new User();
        expectedUser.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        userDAO.findByUsername(username);

        // Assert
        verify(userRepository, times(1)).findByUsername(username);
    }

}
