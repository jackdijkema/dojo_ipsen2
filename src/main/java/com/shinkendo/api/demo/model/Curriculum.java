package com.shinkendo.api.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curriculum")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;
    private String name;
    private String subTitle;
    private String body;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("techniques")
    private Set<Technique> techniques;

    @OneToOne
    @JsonIgnoreProperties("techniques")
    private Rank rank;


}
