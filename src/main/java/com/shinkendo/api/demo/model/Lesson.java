package com.shinkendo.api.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private LocalDate lessonDate;
    private LocalDate recurringEndDate;
    private boolean recurring;
    private String recurringFrequency;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("lessons")
    @JoinTable(
            name = "lesson_user",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("teaches")
    private User teacher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson_technique",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "technique_id")
    )
    @JsonIgnoreProperties("lessons")
    private Set<Technique> techniques;
}
