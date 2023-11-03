package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dto.CurriculumCreateDTO;
import com.shinkendo.api.demo.exception.NotFoundException;
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
        ApiResponse<List<Curriculum>> returns = new ApiResponse<>(curriculumList, HttpStatus.OK);

        assertEquals(response, returns);
    }

}
