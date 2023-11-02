package com.shinkendo.api.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "progress")
public class ProgressReview {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User student;
    private String body;
    private boolean readyToPromote;
}
