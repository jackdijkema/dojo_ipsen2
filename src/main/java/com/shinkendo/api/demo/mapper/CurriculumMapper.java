package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dto.CurriculumResponseDTO;
import com.shinkendo.api.demo.model.Curriculum;
import org.springframework.stereotype.Component;

@Component
public class CurriculumMapper {
    public CurriculumResponseDTO fromEntity(Curriculum curriculum) {
        return CurriculumResponseDTO.builder()
                .id(curriculum.getId().toString())
                .rank(new RankMapper().fromEntity(curriculum.getRank()))
                .techniques(curriculum.getTechniques().stream().map(new TechniqueMapper()::fromEntity).toList())
                .build();
    }
}
