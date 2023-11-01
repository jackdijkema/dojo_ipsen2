package com.shinkendo.api.demo.model;

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
    private String name;
    private String japName;
    private String description;
    private String difficulty;
    @ManyToOne(fetch = FetchType.EAGER)
    private Curriculum curriculum;
}