package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RankSeeder {
    private final RankDao rankDao;
    private final CurriculumDAO curriculumDAO;
    private boolean hasSeeded = false;

    public void seedEmpty() {
        List<Rank> ranks = this.rankDao.findAll();
        if (ranks.stream()
                .anyMatch(rank -> rank.getCurriculum() != null)) {
            System.out.println("Ranks have already been seeded");
            this.hasSeeded = true;
            return;
        }


        for (int i = 1; i < 11; i++) {
            Rank rank = new Rank();
            rank.setUsers(new ArrayList<>());
            rank.setOrderId(i);
            rank.setCurriculum(null);

            try {
                this.rankDao.save(rank);
            }
            catch (Exception e) {
                System.out.println("Couldn't seed: " + e);
            }
        }

    }

    public void seedFull() {
        if (this.hasSeeded) {
            System.out.println("Ranks have already been seeded");
            return;
        }

        List<Curriculum> curriculumList = this.curriculumDAO.findAll();
        List<Rank> ranks = this.rankDao.findAll();

        for (Rank i : ranks) {
            i.setCurriculum(curriculumList.get(i.getOrderId() - 1));
            try {
                this.rankDao.save(i);
            }
            catch (Exception e) {
                System.out.println("Couldn't seed: " + e);
            }
        }

    }
}
