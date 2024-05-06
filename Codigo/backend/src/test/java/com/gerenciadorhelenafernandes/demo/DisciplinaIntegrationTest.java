package com.gerenciadorhelenafernandes.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.services.DisciplinaService;
import com.gerenciadorhelenafernandes.controllers.DisciplinaController;
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

@WebMvcTest(DisciplinaController.class)
@AutoConfigureMockMvc
public class DisciplinaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinaService disciplinaService;

    @Test
    public void testCreateAndFindById() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setIdDisciplina(1L);
        disciplina.setNomeDisciplina("Matemática");
        disciplina.setCargaHoraria(60L);

        // Mock do serviço para simular o salvamento da disciplina
        when(disciplinaService.create(any(Disciplina.class))).thenReturn(disciplina);

        // Criar uma nova disciplina
        mockMvc.perform(post("/disciplina")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(disciplina)))
                .andExpect(status().isCreated());

        // Mock do serviço para simular a busca da disciplina pelo ID
        when(disciplinaService.findById(1L)).thenReturn(disciplina);

        // Verificar se a disciplina foi criada corretamente
        mockMvc.perform(get("/disciplina/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(disciplina)));
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
