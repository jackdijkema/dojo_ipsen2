package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.mapper.CurriculumMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Curriculum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CurriculumControllerTest {
    @InjectMocks
    private CurriculumController curriculumController;

    @Mock
    private CurriculumDAO curriculumDAO;

    @Mock
    private CurriculumMapper curriculumMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllCurriculums() {
        // Mock data
        Curriculum curriculum1 = new Curriculum(UUID.randomUUID(), "Curriculum 1", "Sub 1", "Body 1", new HashSet<>());
        Curriculum curriculum2 = new Curriculum(UUID.randomUUID(), "Curriculum 2", "Sub 2", "Body 2", new HashSet<>());
        List<Curriculum> curriculumList = Arrays.asList(curriculum1, curriculum2);

        when(curriculumDAO.findAll()).thenReturn(curriculumList);

        // Test the API
        ApiResponse<List<Curriculum>> response = curriculumController.all();
        ApiResponse<List<Curriculum>> expected = new ApiResponse<>(curriculumList, HttpStatus.OK);

        assertEquals(response, expected);
    }

    @Test
    public void testRetrieveCurriculumById() {
        // Mock data
        UUID curriculumId = UUID.randomUUID();
        Curriculum curriculum = new Curriculum(curriculumId, "Curriculum 1", "Sub 1", "Body 1", new HashSet());

        when(curriculumDAO.findById(curriculumId)).thenReturn(Optional.of(curriculum));

        // Test the API with a valid ID
        ApiResponse<Curriculum> responseValidId = curriculumController.findById(curriculumId);


        assertEquals(HttpStatus.OK, responseValidId.getStatusCode());
        ApiResponse<Curriculum> expected = new ApiResponse<>(curriculum, HttpStatus.OK);
        assertEquals(expected, responseValidId);

        // Test the API with an invalid ID
        UUID invalidId = UUID.randomUUID();
        when(curriculumDAO.findById(invalidId)).thenReturn(Optional.empty());

        ApiResponse<Curriculum> responseInvalidId = curriculumController.findById(invalidId);

        assertEquals(HttpStatus.NOT_FOUND, responseInvalidId.getStatusCode());
    }
}
