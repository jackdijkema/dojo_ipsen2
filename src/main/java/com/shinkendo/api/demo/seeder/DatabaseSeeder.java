package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.repository.TechniqueSeeder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {
    private final AdminSeeder adminSeeder;
    private final RankSeeder rankSeeder;
    private final CurriculumSeeder curriculumSeeder;
    private final TechniqueSeeder techniqueSeeder;


    private boolean alreadySeeded = false;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (alreadySeeded) {
            return;
        }

        System.out.println("Starting database seed.");

        this.adminSeeder.seed();

        this.rankSeeder.seedEmpty();
        this.curriculumSeeder.seedEmpty();
        this.rankSeeder.seedFull();
        this.techniqueSeeder.seed();
        this.curriculumSeeder.seedFull();


        this.alreadySeeded = true;
    }
}
