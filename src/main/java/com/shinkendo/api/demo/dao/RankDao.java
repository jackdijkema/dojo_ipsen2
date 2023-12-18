package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.Rank;
import com.shinkendo.api.demo.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RankDao {
    private final RankRepository rankRepository;

    public Rank save(Rank rank) {
        return this.rankRepository.save(rank);
    }

    public List<Rank> findAll() {
        return this.rankRepository.findAll();
    }

}
