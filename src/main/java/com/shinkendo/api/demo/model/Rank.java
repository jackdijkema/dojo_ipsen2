package com.shinkendo.api.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    @JsonIgnoreProperties("rank")
    private Curriculum curriculum;

    private int orderId;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("rank")
    private List<User> users;
    
    private String rankName;

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
