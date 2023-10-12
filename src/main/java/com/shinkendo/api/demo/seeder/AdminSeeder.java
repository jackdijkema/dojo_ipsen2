package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminSeeder {
    private final UserDAO userDAO;
    public void seed() {
        this.userDAO.save(User.builder()
                .username("admin")
                .password("admin")
                .role(Role.SUPERADMIN)
                .build());
    }
}
