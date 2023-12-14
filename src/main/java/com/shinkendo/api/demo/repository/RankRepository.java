package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RankRepository extends JpaRepository<Rank, UUID> {
}
