package com.shinkendo.api.demo.dto;

import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Optional<String> username = Optional.empty();
    private Optional<Role> role = Optional.empty();
}
