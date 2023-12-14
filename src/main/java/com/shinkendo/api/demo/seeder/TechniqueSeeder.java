package com.shinkendo.api.demo.seeder;

import com.shinkendo.api.demo.dao.TechniqueDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechniqueSeeder {
    private final TechniqueDAO techniqueDAO;

    public void seed() {

    }
}
