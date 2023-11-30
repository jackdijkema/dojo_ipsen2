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
        Curriculum curriculum1 = Curriculum.builder().id(UUID.randomUUID()).name("Curriculum 1").subTitle( "Sub 1").body("Body 1").techniques(new HashSet<>()).build();
        Curriculum curriculum2 = Curriculum.builder().id(UUID.randomUUID()).name("Curriculum 2").subTitle( "Sub 2").body("Body 2").techniques(new HashSet<>()).build();

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
        Curriculum curriculum = Curriculum.builder().id(UUID.randomUUID()).name("Curriculum 1").subTitle( "Sub 1").body("Body 1").techniques(new HashSet<>()).build();

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

    @Test
    public void testCreateCurriculum() throws NotFoundException {
        // Mock data
        CurriculumCreateDTO createDTO = new CurriculumCreateDTO("New Curriculum", "New Sub", new HashSet<>(), "New Body");
        Curriculum createdCurriculum = Curriculum.builder().id(UUID.randomUUID()).name("New Curriculum").subTitle( "New Sub").body("New Body").techniques(new HashSet<>()).build();

        when(curriculumMapper.toEntity(createDTO)).thenReturn(createdCurriculum);
        when(curriculumDAO.save(createdCurriculum)).thenReturn(createdCurriculum);

        // Test the API
        ApiResponse<Curriculum> response = curriculumController.create(createDTO);
        ApiResponse<Curriculum> expected = new ApiResponse<>(createdCurriculum, HttpStatus.ACCEPTED);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(expected, response);
    }
}
