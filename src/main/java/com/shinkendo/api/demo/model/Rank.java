package com.shinkendo.api.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rank")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    @OneToOne
    private Curriculum curriculum;

}
