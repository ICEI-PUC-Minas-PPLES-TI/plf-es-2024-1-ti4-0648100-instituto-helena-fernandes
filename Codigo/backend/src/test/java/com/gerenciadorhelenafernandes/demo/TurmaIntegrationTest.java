package com.gerenciadorhelenafernandes.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciadorhelenafernandes.models.Turma;
import com.gerenciadorhelenafernandes.services.TurmaService;
import com.gerenciadorhelenafernandes.controllers.TurmaController;
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

@WebMvcTest(TurmaController.class)
@AutoConfigureMockMvc
public class TurmaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TurmaService turmaService;

    @Test
    public void testCreateAndFindById() throws Exception {
        Turma turma = new Turma();
        turma.setId_turma(1L);
        turma.setNome_turma("Turma A");

        // Mock do serviço para simular o salvamento da turma
        when(turmaService.create(any(Turma.class))).thenReturn(turma);

        // Criar uma nova turma
        mockMvc.perform(post("/turma")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(turma)))
                .andExpect(status().isCreated());

        // Mock do serviço para simular a busca da turma pelo ID
        when(turmaService.findById(1L)).thenReturn(turma);

        // Verificar se a turma foi criada corretamente
        mockMvc.perform(get("/turma/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(turma)));
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
