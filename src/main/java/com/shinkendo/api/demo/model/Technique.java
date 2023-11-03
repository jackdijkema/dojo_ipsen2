package com.shinkendo.api.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "technique")
public class Technique {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String japName;
    private String description;
    private String difficulty;

    @ManyToMany
    @JsonIgnoreProperties("techniques")
    private Set<Lesson> lessons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("techniques")
    private Curriculum curriculum;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("techniques")
    private Set<Label> labels;
}