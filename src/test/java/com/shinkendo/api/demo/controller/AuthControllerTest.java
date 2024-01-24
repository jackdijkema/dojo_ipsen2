package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.User;
import com.shinkendo.api.demo.repository.UserRepository;
import com.shinkendo.api.demo.service.AuthenticationService;
import com.shinkendo.api.demo.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "jwt.secret-key=testsecretkey"
})
public class AuthControllerTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    private User mockUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.doReturn("your_mocked_token_value").when(jwtService).getToken();

        mockUser = Mockito.mock(User.class);
        UUID randomUUID = UUID.randomUUID();
        Mockito.when(mockUser.getId()).thenReturn(randomUUID);

        when(userDAO.save(any(User.class))).thenReturn(mockUser);


    }

    @Test
    public void should_create_user_and_return_jwt_with_role() {
        String username = "test";
        String password = "test";

        when(this.userDAO.findByUsername(username)).thenReturn(Optional.empty());

        Optional<String> response = this.authenticationService.register(username, password);
        System.out.println("hi");
    }
}