package com.shinkendo.api.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class LessonResponseDTO {
    private String id;
    private String lessonDate;
    private String teacherName;
    private String teacherId;
    private String note;
    private Collection<TechniqueResponseDTO> techniques;
}
