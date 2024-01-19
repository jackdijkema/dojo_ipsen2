package com.shinkendo.api.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TechniqueResponseDTO {
    public String id;
    public String japaneseName;
    public String englishName;
    public String category;
    public String description;
    public int orderId;
}
