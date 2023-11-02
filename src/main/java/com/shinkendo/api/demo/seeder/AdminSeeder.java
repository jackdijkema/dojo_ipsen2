package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminSeeder {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Value("${super-admin.name}")
    private String superAdminName;

    @Value("${super-admin.password}")
    private String superAdminPassword;

    public void seed() {
        this.userDAO.save(User.builder()
                .username(superAdminName)
                .password(passwordEncoder.encode(superAdminPassword))
                .role(Role.SUPERADMIN)
                .build());
    }
}
