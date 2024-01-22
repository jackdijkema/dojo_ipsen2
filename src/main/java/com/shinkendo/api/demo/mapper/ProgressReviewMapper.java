package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dto.ProgressResponseDTO;
import com.shinkendo.api.demo.dto.RankResponseDTO;
import com.shinkendo.api.demo.dto.TechniqueResponseDTO;
import com.shinkendo.api.demo.model.Lesson;
import com.shinkendo.api.demo.model.Technique;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProgressReviewMapper {
    private final TechniqueMapper techniqueMapper;
    private final RankMapper rankMapper;

    public ProgressResponseDTO fromEntity(User user) {
        RankResponseDTO rank = rankMapper.fromEntity(user.getRank());

        Set<Lesson> lessons = user.getLessons();
        System.out.println(lessons);

        Set<Technique> techniques = lessons
                .stream()
                .map(Lesson::getTechniques).reduce((a, b) -> {
                    a.addAll(b);
                    return a;
                }).orElse(Set.of());
        System.out.println(techniques);


        List<TechniqueResponseDTO> techniquesDto = techniques
                .stream()
                .map(techniqueMapper::fromEntity)
                .toList();

        return ProgressResponseDTO.builder().techniques(techniquesDto).rank(rank).build();
    }
}
