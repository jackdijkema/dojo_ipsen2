package com.shinkendo.api.demo.dto;

import com.shinkendo.api.demo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Optional;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Optional<String> username = Optional.empty();
    private Optional<Role> role = Optional.empty();

    public Optional<String> getUsername() {
        return username;
    }

    public Optional<Role> getRole() {
        return role;
    }
}
