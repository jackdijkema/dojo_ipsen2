package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.Rank;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserSeeder {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final RankDao rankDao;



    public void seedUsers() {
        List<Rank> ranks = this.rankDao.findAll();

        for (int i = 1; i < 10; i++) {
            String username = "student" + i;
            String password = username;
            Rank rank = ranks.get(i);
            seed(username, password, rank);
        }
    }

    private void seed(String username, String password, Rank rank) {
        var user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.STUDENT)
                .rank(rank)
                .build();
        try {
            this.userDAO.save(user);
        } catch (Exception e) {
            System.out.println("couldn't create user account: " + e.getMessage());
        }
    }
}
