package com.shinkendo.api.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonCreateDTO {
    private List<UUID> students;
    private List<UUID> techniques;
    private String lessonDate;
    private String note;
    private UUID teacherId;
    private String endOfRecurring;
}