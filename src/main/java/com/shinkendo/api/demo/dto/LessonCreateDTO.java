package com.shinkendo.api.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonCreateDTO {
    private String name;
    private List<UUID> students;
    private List<UUID> techniques;
    private LocalDate lessonDate;
}