package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.Technique;
import com.shinkendo.api.demo.repository.TechniqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TechniqueDAO {
    TechniqueRepository techniqueRepository;

    public List<Technique> findAll() {
        return techniqueRepository.findAll();
    }
    public Optional<Technique> findById(UUID id) {
        return techniqueRepository.findById(id);
    }
    public Technique create(Technique technique) {
        return techniqueRepository.save(technique);
    }
}
