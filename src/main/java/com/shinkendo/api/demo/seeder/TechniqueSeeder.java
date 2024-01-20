package com.shinkendo.api.demo.seeder;


import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Technique;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TechniqueSeeder {
    private final TechniqueDAO techniqueDAO;
    private final CurriculumDAO curriculumDAO;
    private final Logger logger;

    public void seed() {
        List<Technique> techniques = new ArrayList<>();
        List<Curriculum> curriculumList = this.curriculumDAO.findAll();

        if (!techniqueDAO.findAll().isEmpty()) {
            logger.info("Techniques have already been seeded");
            return;
        }

        boolean shouldSkip = true;
        BufferedReader reader;
        try {
            URL resource = getClass().getClassLoader().getResource("techniques.csv");

            assert resource != null;
            reader = new BufferedReader(new FileReader(resource.getFile()));
            String line = reader.readLine();

            while (line != null) {
                if (shouldSkip) {
                    shouldSkip = false;
                    line = reader.readLine();
                    continue;
                }

                String[] lineArray = line.split(",");

                Technique technique = new Technique();
                technique.setJapaneseName(lineArray[0]);
                technique.setEnglishName(lineArray[1]);
                technique.setCategory(lineArray[2]);
                technique.setDescription(lineArray[3].strip());
                technique.setOrderId(Integer.parseInt(lineArray[4].strip()));

                for (Curriculum i : curriculumList) {
                    if (i.getRank().getOrderId() == technique.getOrderId()) {
                        technique.setCurriculum(i);
                        break;
                    }
                }


                techniques.add(technique);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }

        for (Technique i : techniques) {
            try {
                this.techniqueDAO.save(i);
            } catch (Exception e) {
                logger.warn("Couldn't seed: " + e);
            }
        }
    }


}
