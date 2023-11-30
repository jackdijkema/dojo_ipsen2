package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dto.UserCreateDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public User toEntity(UserCreateDTO userCreateDTO) {
        return User.builder()
                .username(userCreateDTO.getUsername())
                .password(userCreateDTO.getPassword())
                .build();
    }

    public UserResponseDTO fromEntity(User user) {
        return UserResponseDTO
                .builder()
                .username(user.getUsername())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }
}
