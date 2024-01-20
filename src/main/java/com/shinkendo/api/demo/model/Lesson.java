package com.shinkendo.api.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lesson")
@EqualsAndHashCode(exclude = {"students", "teacher", "techniques"})
@ToString(exclude = {"students", "teacher", "techniques"})
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate lessonDate;
    private String note;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("lessons")
    @JoinTable(
            name = "lesson_user",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
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
