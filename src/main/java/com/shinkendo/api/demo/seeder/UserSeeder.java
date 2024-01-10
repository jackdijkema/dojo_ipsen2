package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.model.Rank;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class UserSeeder {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final RankDao rankDao;

    private final String[] nederlandseGebruikersnamen = {
            "Jan",
            "Annie",
            "Piet",
            "Marie",
            "Henk",
            "Ineke",
            "Johan",
            "Wilma",
            "Peter",
            "Elly",
            "Erik",
            "Saskia",
            "Dirk",
            "Margreet",
            "Theo",
            "Carla",
            "Frank",
            "Linda",
            "Bert",
            "Ria"
    };


    public void seedUsers() {
        List<Rank> ranks = this.rankDao.findAll();
        Map<Integer, List<User>> userMap = new HashMap<>();

        for (String gebruikernaam : nederlandseGebruikersnamen) {
            String username = gebruikernaam;
            String password = username;

            Random generator = new Random();
            int randomIndex = generator.nextInt(ranks.size());
            Rank rank = ranks.get(randomIndex);

            User user = seed(username, password, rank);
            try {
                this.userDAO.save(user);
            } catch (Exception e) {
                System.out.println("couldn't create user account: " + e.getMessage());
            }

            if(userMap.containsKey(randomIndex)) {
                List<User> oude = userMap.get(randomIndex);
                oude.add(user);
                userMap.put(randomIndex, oude);
            } else {
                List<User> seededUser = new ArrayList<>();
                seededUser.add(user);
                userMap.put(randomIndex, seededUser);
            }

//            this.rankDao.save(rank);
        }

        for(Map.Entry<Integer, List<User>> entry : userMap.entrySet()) {
            ranks.get(entry.getKey()).setUsers(entry.getValue());
        }

        for(Rank rank : ranks) {
            this.rankDao.save(rank);
        }

    }

    private User seed(String username, String password, Rank rank) {
        var user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.STUDENT)
                .rank(rank)
                .build();
        return user;
    }
}
