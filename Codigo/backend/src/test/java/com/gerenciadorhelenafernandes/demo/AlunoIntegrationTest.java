package com.gerenciadorhelenafernandes.demo;

import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.services.AlunoServices;
import com.gerenciadorhelenafernandes.controllers.DisciplinaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DisciplinaController.class)
@AutoConfigureMockMvc
public class AlunoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoServices alunoServices;

    @Test
    public void testCreateAluno() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome_aluno("Teste");
        aluno.setEmailAluno("teste@example.com");
        aluno.setSenha_aluno("senha123");

        mockMvc.perform(post("/aluno")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(aluno)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateAluno() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome_aluno("Teste");
        aluno.setEmailAluno("teste@example.com");
        aluno.setSenha_aluno("senha123");

        mockMvc.perform(put("/aluno/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(aluno)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteAluno() throws Exception {
        mockMvc.perform(delete("/aluno/1"))
                .andExpect(status().isNoContent());
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
