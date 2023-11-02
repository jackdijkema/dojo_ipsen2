package com.shinkendo.api.demo.dao;

import com.shinkendo.api.demo.model.ProgressReview;
import com.shinkendo.api.demo.repository.ProgressReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProgressReviewDAO {

    ProgressReviewRepository progressReviewRepository;

    public Optional<ProgressReview> findById(UUID id){
        return progressReviewRepository.findById(id);
    }

    public ProgressReview create(ProgressReview progressReview) {return progressReviewRepository.save(progressReview);}
}
