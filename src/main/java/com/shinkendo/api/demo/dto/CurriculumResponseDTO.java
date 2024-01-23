package com.shinkendo.api.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumResponseDTO {
    private String id;
    private RankResponseDTO rank;
    private Collection<TechniqueResponseDTO> techniques;
}
