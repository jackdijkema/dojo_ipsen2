package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.mapper.CurriculumMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

public class CurriculumControllerTest {
    private CurriculumController curriculumController;

    @Mock
    private CurriculumDAO curriculumDAO;

    @Mock
    private CurriculumMapper curriculumMapper;

    @BeforeEach
    public void setup() {
        curriculumController = new CurriculumController(curriculumMapper, curriculumDAO);
    }


}
