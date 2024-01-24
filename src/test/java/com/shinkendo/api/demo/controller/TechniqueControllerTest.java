package com.shinkendo.api.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.model.Technique;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class TechniqueControllerTest {

    @Mock
    private TechniqueDAO techniqueDAO;

    @InjectMocks
    private TechniqueController techniqueController;

    @Test
    void findAll() throws Exception {
        when(techniqueDAO.findAll()).thenReturn(Collections.singletonList(createSampleTechnique()));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(techniqueController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/technique")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void findById() throws Exception {
        Technique sampleTechnique = createSampleTechnique();
        when(techniqueDAO.findById(any(UUID.class))).thenReturn(Optional.of(sampleTechnique));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(techniqueController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/technique/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void create() throws Exception {
        Technique sampleTechnique = createSampleTechnique();
        when(techniqueDAO.save(any(Technique.class))).thenReturn(sampleTechnique);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(techniqueController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/technique")
                        .content(asJsonString(sampleTechnique))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(print());
    }

    private Technique createSampleTechnique() {
        Technique technique = new Technique();
        technique.setId(UUID.randomUUID());
        technique.setJapaneseName("Sample Japanese Name");
        technique.setEnglishName("Sample English Name");
        technique.setCategory("Sample Category");
        technique.setDescription("Sample Description");
        technique.setOrderId(1);
        return technique;
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
