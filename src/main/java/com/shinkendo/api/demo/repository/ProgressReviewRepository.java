package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.ProgressReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProgressReviewRepository extends JpaRepository<ProgressReview, UUID> {
}
