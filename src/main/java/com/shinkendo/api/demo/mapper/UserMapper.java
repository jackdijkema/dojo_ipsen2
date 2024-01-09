package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.dto.UserCreateDTO;
import com.shinkendo.api.demo.dto.UserResponseDTO;
import com.shinkendo.api.demo.model.Rank;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RankDao rankDao;

    public User toEntity(UserCreateDTO userCreateDTO) {
        Rank rank = rankDao.findById(UUID.fromString(userCreateDTO.getRank()));
        System.out.println(rank.getRankName());
        Role role = Role.valueOf(userCreateDTO.getRole());

        return User.builder()
                .username(userCreateDTO.getUsername())
                .password(userCreateDTO.getPassword())
                .role(role)
                .rank(rank)
                .build();
    }

    public UserResponseDTO fromEntity(User user) {
        Rank rank = user.getRank();

        UserResponseDTO.UserResponseDTOBuilder res = UserResponseDTO
                .builder()
                .username(user.getUsername())
                .id(user.getId())
                .role(user.getRole())
                .rankName("No Rank");


        if (rank != null) {
            res.rankName(rank.getRankName());
        }

        return res.build();
    }
}
