package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.LessonDAO;
import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.dto.LessonCreateDTO;
import com.shinkendo.api.demo.mapper.LessonMapper;
import com.shinkendo.api.demo.model.Lesson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonDAO lessonDao;

    @MockBean
    private LessonMapper lessonMapper;

    @MockBean
    private TechniqueDAO techniqueDAO;
@Test
@WithMockUser(username="admin",authorities={"SUPERADMIN"})
public void should_create_lesson() throws Exception {
      LessonCreateDTO lessonCreateDTO = new LessonCreateDTO();
      lessonCreateDTO.setTechniques(List.of(UUID.randomUUID()));
      lessonCreateDTO.setLessonDate("2024-10-10");
      lessonCreateDTO.setTeacherId(UUID.randomUUID());

      lessonCreateDTO.setTechniques(List.of(UUID.randomUUID()));
      lessonCreateDTO.setLessonDate("2024-10-10");
      lessonCreateDTO.setTeacherId(UUID.randomUUID());
      Lesson expectedLesson = new Lesson();


      Mockito.when(lessonDao.save(expectedLesson)).thenReturn(expectedLesson);
      Mockito.when(techniqueDAO.findById(lessonCreateDTO.getTechniques().get(0))).thenReturn(Optional.empty());

      mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/lesson")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(new ObjectMapper().writeValueAsString(lessonCreateDTO)))
              .andExpect(MockMvcResultMatchers.status().isOk());
  }

}