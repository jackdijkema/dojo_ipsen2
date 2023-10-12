package com.shinkendo.api.demo.seeder;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {
    private boolean alreadySeeded = false;
    private final AdminSeeder adminSeeder;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        if(alreadySeeded){
            return;
        }

        this.adminSeeder.seed();

        this.alreadySeeded = true;
    }
}
