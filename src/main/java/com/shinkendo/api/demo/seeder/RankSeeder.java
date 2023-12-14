package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Rank;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class RankSeeder {
    private final RankDao rankDao;

    public void seed() {
        for (int i = 0; i < 10; i++) {
            Rank rank = new Rank();
            rank.setUsers(new ArrayList<>());
            rank.setOrderId(i);
            rank.setCurriculum(new Curriculum());
        }

    }
}
