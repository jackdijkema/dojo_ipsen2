package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.UserDAO;
import com.shinkendo.api.demo.dto.ProgressReviewDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Role;
import com.shinkendo.api.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProgressReviewControllerTest {
    @InjectMocks
    private ProgressReviewController progressReviewController;

    @Mock
    private UserDAO userDAO;

    @Mock
    private ProgressReviewDAO progressReviewDAO;

    @Mock
    private ProgressReviewMapper progressReviewMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_create_progress_review() throws NotFoundException {
        //Mock data
        User testUser = User.builder()
                .username("testUser")
                .password("test")
                .role(Role.STUDENT)
                .build();

        ProgressReviewDTO createProgressReviewDTO = new ProgressReviewDTO(testUser.getId(), "student bakt er niks van", false);
        ProgressReview createdProgressReview = new ProgressReview(UUID.randomUUID(), testUser, "student bakt er niks van", false);

        when(progressReviewMapper.toEntity(createProgressReviewDTO)).thenReturn(createdProgressReview);
        when(progressReviewDAO.create(createdProgressReview)).thenReturn(createdProgressReview);

        //Test the api
        ApiResponse<ProgressReview> response = progressReviewController.create(createProgressReviewDTO);
        ApiResponse<ProgressReview> expected = new ApiResponse<>(createdProgressReview, HttpStatus.ACCEPTED);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    public void should_return_list_of_progress_reviews() {
        //Mock data
        User testUser1 = User.builder()
                .username("testUser1")
                .password("test")
                .role(Role.STUDENT)
                .build();


        UUID testProgressReviewId1 = UUID.randomUUID();
        UUID testProgressReviewId2 = UUID.randomUUID();
        ProgressReview createdProgressReview1 = new ProgressReview(testProgressReviewId1, testUser1, "student bakt er niks van", false);
        ProgressReview createdProgressReview2 = new ProgressReview(testProgressReviewId2, testUser1, "student kan slaan met stok", true);
        List<ProgressReview> progressReviewList = Arrays.asList(createdProgressReview1, createdProgressReview2);

        when(progressReviewDAO.findAll()).thenReturn(progressReviewList);

        //Test the API
        ApiResponse<List<ProgressReview>> response = progressReviewController.findAll();
        ApiResponse<List<ProgressReview>> expected = new ApiResponse<>(progressReviewList, HttpStatus.OK);

        assertEquals(response, expected);
    }


}
