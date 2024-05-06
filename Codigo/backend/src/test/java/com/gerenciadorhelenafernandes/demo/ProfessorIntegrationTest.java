package com.gerenciadorhelenafernandes.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciadorhelenafernandes.models.Professor;
import com.gerenciadorhelenafernandes.services.ProfessorService;
import com.gerenciadorhelenafernandes.controllers.ProfessorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfessorController.class)
@AutoConfigureMockMvc
public class ProfessorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @Test
    public void testCreateAndFindById() throws Exception {
        Professor professor = new Professor();
        professor.setId_professor(1L);
        professor.setNome_professor("João Silva");
        professor.setCpf_professor("123456789");
        professor.setData_nascimento("01/01/1980");
        professor.setFormacao_professor("Licenciatura em Matemática");
        professor.setEmail_professor("joao@example.com");

        // Mock do serviço para simular o salvamento do professor
        when(professorService.create(any(Professor.class))).thenReturn(professor);

        // Criar um novo professor
        mockMvc.perform(post("/professor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(professor)))
                .andExpect(status().isCreated());

        // Mock do serviço para simular a busca do professor pelo ID
        when(professorService.findById(1L)).thenReturn(professor);

        // Verificar se o professor foi criado corretamente
        mockMvc.perform(get("/professor/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(professor)));
    }

    // Método utilitário para converter um objeto Java em uma string JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
