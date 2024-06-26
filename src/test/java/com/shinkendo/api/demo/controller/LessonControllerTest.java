package com.shinkendo.api.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "cors.allowed-origins=http://localhost:4200/"
})
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
    @WithMockUser(username = "admin", authorities = {"SENSEI"})
    public void should_create_lesson() throws Exception {
        LessonCreateDTO lessonCreateDTO = new LessonCreateDTO();
        lessonCreateDTO.setTechniques(List.of(UUID.randomUUID()));
        lessonCreateDTO.setLessonDate("2024-10-10");
        lessonCreateDTO.setTeacherId(UUID.randomUUID());

        Lesson expectedLesson = new Lesson();
        lessonCreateDTO.setTechniques(List.of(UUID.randomUUID()));
        lessonCreateDTO.setLessonDate("2024-10-10");
        lessonCreateDTO.setTeacherId(UUID.randomUUID());


        Mockito.when(lessonDao.save(expectedLesson)).thenReturn(expectedLesson);
        Mockito.when(techniqueDAO.findById(lessonCreateDTO.getTechniques().get(0))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/lesson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lessonCreateDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SENSEI"})
    public void should_retrieve_lesson_successfully() throws Exception {
        Lesson lesson = new Lesson();
        UUID lessonId = UUID.randomUUID();
        lesson.setId(lessonId);

        Mockito.when(lessonDao.findById(lessonId)).thenReturn(Optional.of(lesson));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lesson/" + lessonId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SENSEI"})
    public void should_edit_lesson() throws Exception {
        LessonCreateDTO initialLessonCreateDTO = new LessonCreateDTO();
        initialLessonCreateDTO.setTechniques(List.of(UUID.randomUUID()));
        initialLessonCreateDTO.setLessonDate("2024-10-10");
        initialLessonCreateDTO.setTeacherId(UUID.randomUUID());

        Lesson initialLesson = new Lesson();
        initialLesson.setId(UUID.randomUUID());

        Mockito.when(lessonDao.save(Mockito.any(Lesson.class))).thenReturn(initialLesson);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/lesson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(initialLessonCreateDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        LessonCreateDTO updatedLessonCreateDTO = new LessonCreateDTO();
        updatedLessonCreateDTO.setTechniques(List.of(UUID.randomUUID()));
        updatedLessonCreateDTO.setLessonDate("2025-10-10");
        updatedLessonCreateDTO.setTeacherId(UUID.randomUUID());

        Lesson updatedLesson = new Lesson();
        updatedLesson.setId(initialLesson.getId());

        Mockito.when(lessonDao.findById(initialLesson.getId())).thenReturn(Optional.of(initialLesson));
        Mockito.when(lessonMapper.toEntity(Mockito.any(LessonCreateDTO.class))).thenReturn(new Lesson());
        Mockito.when(lessonDao.save(Mockito.any(Lesson.class))).thenReturn(updatedLesson);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/lesson/" + initialLesson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedLessonCreateDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SENSEI"})
    public void should_fail_with_incorrect_inputs() throws Exception {

        LessonCreateDTO lessonCreateDTO = new LessonCreateDTO();
        lessonCreateDTO.setTechniques(List.of(UUID.randomUUID()));
        lessonCreateDTO.setLessonDate(null);
        lessonCreateDTO.setTeacherId(null);

        Lesson expectedLesson = new Lesson();
        lessonCreateDTO.setTechniques(List.of(UUID.randomUUID()));
        lessonCreateDTO.setLessonDate(null);
        lessonCreateDTO.setTeacherId(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/lesson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lessonCreateDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SENSEI"})
    public void should_fail_if_lesson_is_not_found() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        Mockito.when(lessonDao.findById(nonExistentId)).thenReturn(Optional.empty());


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lesson/" + nonExistentId))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"SENSEI"})
    public void should_fail_when_deleting_non_existent_lesson() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        Mockito.when(lessonDao.findById(nonExistentId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/lesson/" + nonExistentId))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
