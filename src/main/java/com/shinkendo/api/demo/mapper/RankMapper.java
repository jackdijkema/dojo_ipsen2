package com.shinkendo.api.demo.mapper;

import com.shinkendo.api.demo.dto.RankResponseDTO;
import com.shinkendo.api.demo.model.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RankMapper {
    public RankResponseDTO fromEntity(Rank rank) {
        return RankResponseDTO.builder()
                .id(rank.getId())
                .rankName(rank.getRankName())
                .orderId(rank.getOrderId())
                .build();
    }
}
