package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.dto.CurriculumCreateDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Technique;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CurriculumMapper {
    private final TechniqueDAO techniqueDAO;
    public Curriculum toEntity(CurriculumCreateDTO curriculumCreateDTO) throws NotFoundException {
        HashSet<Technique> techniqueList = new HashSet<>();
        for (UUID id : curriculumCreateDTO.getTechniqueIds()) {
            Optional<Technique> technique = techniqueDAO.findById(id);
            if (technique.isEmpty()) throw new NotFoundException("technique" + id + ", Not found");
            techniqueList.add(technique.get());
        }

        return Curriculum
                .builder()
                .name(curriculumCreateDTO.getName())
                .subTitle(curriculumCreateDTO.getSub())
                .techniques(techniqueList)
                .body(curriculumCreateDTO.getBody())
                .build();

    }
}
