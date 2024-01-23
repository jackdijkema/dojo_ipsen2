package com.shinkendo.api.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    @JsonIgnore
    private Curriculum curriculum;

    private String japaneseName;
    private String englishName;
    private String category;
    private String description;
    private int orderId;

    public Technique(String japaneseName, String englishName, String category, String description, Curriculum curriculum) {
        this.japaneseName = japaneseName;
        this.englishName = englishName;
        this.category = category;
        this.description = description;
        this.curriculum = curriculum;
    }
}