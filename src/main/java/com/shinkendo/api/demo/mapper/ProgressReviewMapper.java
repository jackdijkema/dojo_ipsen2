package com.shinkendo.api.demo.mapper;


import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.ProgressReviewDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.ProgressReview;
import com.shinkendo.api.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProgressReviewMapper {

    private final UserDAO userDao;

    public ProgressReview toEntity(ProgressReviewDTO progressReviewDTO) throws NotFoundException {
        Optional<User> user = userDao.findById(progressReviewDTO.getStudentId());
        if (user.isEmpty()) {
            throw new NotFoundException("User with ID" + progressReviewDTO.getStudentId() + "was not found");
        }
        return ProgressReview.builder()
                .body(progressReviewDTO.getBody())
                .student(user.get())
                .readyToPromote(progressReviewDTO.isReadyToPromote())
                .build();

    }
}
