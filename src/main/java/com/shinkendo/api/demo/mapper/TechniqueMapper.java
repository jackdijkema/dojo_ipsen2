package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dto.TechniqueResponseDTO;
import com.shinkendo.api.demo.model.Technique;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechniqueMapper {
    public TechniqueResponseDTO fromEntity(Technique technique) {
        return TechniqueResponseDTO.builder()
                .id(technique.getId().toString())
                .japaneseName(technique.getJapaneseName())
                .englishName(technique.getEnglishName())
                .category(technique.getCategory())
                .description(technique.getDescription())
                .orderId(technique.getOrderId())
                .build();
    }
}
