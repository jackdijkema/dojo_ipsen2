package com.shinkendo.api.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curriculum")
@ToString(exclude = {"rank", "techniques"})
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JsonIgnoreProperties("curriculum")
    private Rank rank;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "curriculum_id")
    @JsonIgnoreProperties("curriculum")
    private List<Technique> techniques;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", rank=" + (rank != null ? rank.getId() : null) + // Extracting only the ID to avoid circular reference
                ", techniques=" + techniques +
                '}';
    }
}
