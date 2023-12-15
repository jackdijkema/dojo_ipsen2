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

    public void seedEmpty() {
        for (int i = 1; i < 11; i++) {
            Rank rank = new Rank();
            rank.setUsers(new ArrayList<>());
            rank.setOrderId(i);
            rank.setCurriculum(null);
            this.rankDao.save(rank);
        }

    }

    public void seedFull() {
        List<Curriculum> curriculumList = this.curriculumDAO.findAll();
        List<Rank> ranks = this.rankDao.findAll();

        for (Rank i : ranks) {
            i.setCurriculum(curriculumList.get(i.getOrderId() - 1));
            this.rankDao.save(i);
        }

    }
}
