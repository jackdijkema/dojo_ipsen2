package com.shinkendo.api.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankResponseDTO {
    private UUID id;
    private String rankName;
    private int orderId;
}
