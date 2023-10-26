package com.shinkendo.api.demo.dao;


import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.repository.CurriculumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CurriculumDAO {
    private final CurriculumRepository curriculumRepository;

    public Optional<Curriculum> findById(UUID id) { return curriculumRepository.findById(id);}

    public List<Curriculum> findAll() { return  curriculumRepository.findAll();}

    public Curriculum create(Curriculum createCurriculum) { return curriculumRepository.save(createCurriculum);}
    public Curriculum update(Curriculum updateCurriculum) { return curriculumRepository.save(updateCurriculum);}
}
