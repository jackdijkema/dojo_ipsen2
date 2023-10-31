package com.shinkendo.api.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDTO {
    String name;
    String body;
}
