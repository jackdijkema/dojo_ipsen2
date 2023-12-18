package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Rank;
import com.shinkendo.api.demo.model.Technique;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CurriculumSeeder {
    private final CurriculumDAO curriculumDao;
    private final RankDao rankDao;
    private final TechniqueDAO techniqueDAO;
    private boolean hasSeeded = false;

    public void seedEmpty() {
        List<Rank> ranks = this.rankDao.findAll();

        if (this.curriculumDao.findAll().stream()
                .anyMatch(curriculum -> curriculum.getTechniques() == null)) {
            hasSeeded = true;
            System.out.println("Curriculums have already been seeded");
            return;
        }

        for (int i = 1; i < 11; i++) {
            Curriculum curriculum = new Curriculum();
            curriculum.setRank(ranks.get(i-1));
            curriculum.setTechniques(null);
            try {
                this.curriculumDao.save(curriculum);
            }
            catch (Exception e) {
                System.out.println("Couldn't seed: " + e);
            }
        }
    }

    public void seedFull() {
        if (this.hasSeeded) {
            System.out.println("Curriculums have already been seeded");
            return;
        }

        List<Curriculum> curriculumList = this.curriculumDao.findAll();
        List<Technique> techniques = this.techniqueDAO.findAll();

        for (Curriculum i : curriculumList) {
            List<Technique> references = new ArrayList<>();
            for (Technique j : techniques) {
                if (j.getCurriculumId().getRank().getOrderId() == i.getRank().getOrderId()) {
                    references.add(j);
                }
            }
            i.setTechniques(references);

            try {
                this.curriculumDao.save(i);
            }
            catch (Exception e) {
                System.out.println("Couldn't seed: " + e);
            }
        }

    }

}

