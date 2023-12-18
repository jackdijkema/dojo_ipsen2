package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        var admin = User.builder()
                .username(superAdminName)
                .password(passwordEncoder.encode(superAdminPassword))
                .role(Role.SUPERADMIN)
                .build();
        try {
            this.userDAO.save(admin);
        } catch (Exception e) {
            System.out.println("couldn't create admin account: " + e.getMessage());
        }
    }
}