package com.shinkendo.api.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ProgressResponseDTO {
    public Collection<TechniqueResponseDTO> techniques;
    public RankResponseDTO rank;
}
