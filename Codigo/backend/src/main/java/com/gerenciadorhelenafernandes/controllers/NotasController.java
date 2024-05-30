package com.gerenciadorhelenafernandes.controllers;

import com.gerenciadorhelenafernandes.models.Notas;
import com.gerenciadorhelenafernandes.services.NotasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notas")
@CrossOrigin("*")
public class NotasController {

    private final NotasService notasService;

    public NotasController(NotasService notasService) {
        this.notasService = notasService;
    }

    // recuperar nota especifica
    @GetMapping("/{id}")
    public ResponseEntity<Notas> getNotasById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notasService.findById(id));
    }

    // recuperar todas as notas
    @GetMapping
    public ResponseEntity<List<Notas>> listarTodas() {
        List<Notas> notasList = notasService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(notasList);
    }

    // salvar notas
    @PostMapping
    public ResponseEntity<?> saveMultipleNotas(@RequestBody List<Notas> notasList) {
        try {
            if (notasList == null || notasList.isEmpty()) {
                return ResponseEntity.badRequest().body("A lista de notas não pode estar vazia");
            }

            notasService.saveMultipleNotas(notasList);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar múltiplas notas: " + e.getMessage());
        }
    }

    // alterar nota específica pelo id da nota
    @PutMapping("/{id_notas}")
    public ResponseEntity<Notas> update(@RequestBody Notas notas, @PathVariable Long id_notas) {
        notas = notasService.update(id_notas, notas);
        return ResponseEntity.status(HttpStatus.OK).body(notas);
    }

    // deletar nota específica pelo id da nota
    @DeleteMapping("/{id_notas}")
    public ResponseEntity<?> delete(@PathVariable Long id_notas) {
        notasService.delete(id_notas);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{idNota}/turma/{idTurma}/disciplina/{idDisciplina}")
    public ResponseEntity<?> editarNota(@PathVariable Long idTurma, @PathVariable Long idDisciplina,
            @PathVariable Long idNota, @RequestBody Map<String, Double> requestBody) {
        try {
            Double notaProva1 = requestBody.get("notaProva1");
            Double notaProva2 = requestBody.get("notaProva2");
            Double notaProva3 = requestBody.get("notaProva3");
            Double notaTrabalho1 = requestBody.get("notaTrabalho1");
            Double notaTrabalho2 = requestBody.get("notaTrabalho2");
            Double notaTrabalho3 = requestBody.get("notaTrabalho3");

            notasService.editarNota(idNota, notaProva1, notaProva2, notaProva3, notaTrabalho1, notaTrabalho2,
                    notaTrabalho3);

            return ResponseEntity.status(HttpStatus.OK).body("Nota editada com sucesso.");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros incompletos.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // encontrar uma nota de um aluno específico
    @GetMapping("/aluno/{idAluno}/turmas/{idTurma}")
    public ResponseEntity<Notas> getNotasByAlunoAndTurma(@PathVariable Long idAluno, @PathVariable Long idTurma) {
        Notas notas = notasService.findNotasByAlunoIdAndTurmaId(idAluno, idTurma);
        if (notas != null) {
            return ResponseEntity.status(HttpStatus.OK).body(notas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
