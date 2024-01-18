package com.shinkendo.api.demo.dto;

import com.shinkendo.api.demo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String username;
    private Role role;
    private String rankName;
    private String rankId;
}
