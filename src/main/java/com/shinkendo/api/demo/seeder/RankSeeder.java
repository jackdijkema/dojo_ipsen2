package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Rank;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RankSeeder {
    private final RankDao rankDao;
    private final CurriculumDAO curriculumDAO;
    private final Logger logger;

    private boolean hasSeeded = false;


    public void seedEmpty() {
        List<Rank> ranks = this.rankDao.findAll();
        if (ranks.stream().anyMatch(rank -> rank.getCurriculum() != null)) {
            logger.info("Ranks have already been seeded");
            this.hasSeeded = true;
            return;
        }

        String[] rankNames = {"Ichimonhji", "Jiho", "Santen", "Shiho", "Kirigami", "Gohou", "Gohoumokuroku", "Hyaku-e", "Ren-e", "Ji-e"};

        for (int i = 0; i < rankNames.length; i++) {
            Rank rank = new Rank();
            rank.setUsers(new ArrayList<>());
            rank.setOrderId(i + 1);
            rank.setCurriculum(null);
            rank.setRankName(rankNames[i]);

            try {
                this.rankDao.save(rank);
            } catch (Exception e) {
                logger.warn("Couldn't seed: " + e);
            }
        }

    }

    public void seedFull() {
        if (this.hasSeeded) {
            logger.info("Ranks have already been seeded");
            return;
        }

        List<Curriculum> curriculumList = this.curriculumDAO.findAll();
        List<Rank> ranks = this.rankDao.findAll();

        for (Rank rank : ranks) {
            rank.setCurriculum(curriculumList.get(rank.getOrderId() - 1));

            try {
                this.rankDao.save(rank);
            } catch (Exception e) {
                logger.warn("Couldn't seed: " + e);
            }
        }

    }
}
