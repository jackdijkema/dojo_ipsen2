package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminSeeder {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger;

    @Value("${super-admin.name}")
    private String SENSEIName;

    @Value("${super-admin.password}")
    private String SENSEIPassword;

    public void seed() {
        var admin = User.builder()
                .username(SENSEIName)
                .password(passwordEncoder.encode(SENSEIPassword))
                .role(Role.SENSEI)
                .editable(false)
                .build();
        try {
            this.userDAO.save(admin);
        } catch (Exception e) {
            logger.warn("couldn't create admin account: " + e.getMessage());
        }
    }
}