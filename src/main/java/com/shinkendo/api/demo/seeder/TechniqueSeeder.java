package com.shinkendo.api.demo.repository;


import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Technique;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TechniqueSeeder {
    private final TechniqueDAO techniqueDAO;
    private final CurriculumDAO curriculumDAO;

    public void seed() {
        List<Technique> techniques = new ArrayList<>();
        List<Curriculum> curriculumList = this.curriculumDAO.findAll();

        boolean shouldSkip = true;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/java/com/shinkendo/api/demo/seeder/techniques.csv"));
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
                        technique.setCurriculumId(i);
                        break;
                    }
                }


                techniques.add(technique);

                System.out.println(line);
                line = reader.readLine();
            }

            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for (Technique i : techniques) {
            this.techniqueDAO.save(i);
        }
    }


}
