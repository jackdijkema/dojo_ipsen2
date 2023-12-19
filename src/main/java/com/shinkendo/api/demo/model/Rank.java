package com.shinkendo.api.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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

    @OneToOne(mappedBy = "rank", cascade = CascadeType.ALL)
    private Curriculum curriculum;

    private int orderId;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
