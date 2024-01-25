package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.dto.UserCreateDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.model.Rank;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RankDao rankDao;
    private final PasswordEncoder passwordEncoder;

    public User toEntity(UserCreateDTO userCreateDTO) {
        User.UserBuilder newUser = User.builder()
                .username(userCreateDTO.getUsername())
                .editable(true);

        if (userCreateDTO.getPassword() != null && !userCreateDTO.getPassword().isEmpty()) {
            newUser.password(passwordEncoder.encode(userCreateDTO.getPassword()));
        }

        try {
            Rank rank = rankDao.findById(UUID.fromString(userCreateDTO.getRank()));
            newUser.rank(rank);
        } catch (Exception e) {
            newUser.rank(null);
        }

        try {
            Role role = Role.valueOf(userCreateDTO.getRole());
            newUser.role(role);
        } catch (Exception e) {
            newUser.role(Role.STUDENT);
        }

        return newUser.build();
    }

    public UserResponseDTO fromEntity(User user) {
        Rank rank = user.getRank();

        UserResponseDTO.UserResponseDTOBuilder res = UserResponseDTO
                .builder()
                .username(user.getUsername())
                .id(user.getId())
                .role(user.getRole())
                .rankId("")
                .rankName("No Rank")
                .editable(user.isEditable());


        if (rank != null) {
            res.rankId(rank.getId().toString());
            res.rankName(rank.getRankName());
        }

        return res.build();
    }
}
