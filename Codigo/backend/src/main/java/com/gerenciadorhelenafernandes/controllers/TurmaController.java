package com.gerenciadorhelenafernandes.controllers;

import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.models.Turma;
import com.gerenciadorhelenafernandes.services.NotasService;
import com.gerenciadorhelenafernandes.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private NotasService notasService;

    @GetMapping("/{id_turma}")
    public ResponseEntity<?> findById(@PathVariable("id_turma") Long idTurma) {
        return ResponseEntity.status(200).body(turmaService.findById(idTurma));
    }

    @GetMapping("/{id_turma}/alunos")
    public ResponseEntity<List<Aluno>> getAlunosByTurma(@PathVariable("id_turma") Long idTurma) {
        Turma turma = turmaService.findById(idTurma);
        return ResponseEntity.ok(turma.getAlunos());
    }

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(
            @RequestParam(value = "nome", required = false) String nome) {
        List<Turma> lista;
        if (nome != null) {
            lista = turmaService.findAll();
        } else {
            lista = turmaService.findAll();
        }
        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping
    public ResponseEntity<Turma> create(@RequestBody Turma turma) {
        turma = turmaService.create(turma);
        return ResponseEntity.status(201).body(turma);
    }

    @PutMapping("/{id_turma}")
    public ResponseEntity<Turma> update(@PathVariable Long id_turma, @RequestBody Turma turma) {
        turma.setIdTurma(id_turma); // Garante que o ID seja o mesmo informado na URL
        turma = turmaService.update(id_turma, turma);
        return ResponseEntity.status(200).body(turma);
    }

    @DeleteMapping("/{id_turma}")
    public ResponseEntity<?> delete(@PathVariable Long id_turma) {
        turmaService.delete(id_turma);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id_turma}/alunos")
    public ResponseEntity<?> adicionarAlunos(@PathVariable Long id_turma, @RequestBody List<Long> alunosIds) {
        turmaService.adicionarAluno(id_turma, alunosIds);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id_turma}/alunos")
    public ResponseEntity<?> removerAlunos(@PathVariable Long id_turma, @RequestBody List<Long> alunosIds) {
        turmaService.removerAluno(id_turma, alunosIds);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id_turma}/professores")
    public ResponseEntity<?> adicionarProfessores(@PathVariable Long id_turma, @RequestBody List<Long> professoresIds) {
        turmaService.adicionarProfessor(id_turma, professoresIds);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id_turma}/professores")
    public ResponseEntity<?> removerProfessores(@PathVariable Long id_turma, @RequestBody List<Long> professoresIds) {
        turmaService.removerProfessor(id_turma, professoresIds);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id_turma}/disciplinas")
    public ResponseEntity<?> adicionarDisciplinas(@PathVariable Long id_turma, @RequestBody List<Long> disciplinasIds) {
        turmaService.adicionarDisciplina(id_turma, disciplinasIds);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id_turma}/disciplinas")
    public ResponseEntity<?> removerDisciplinas(@PathVariable Long id_turma, @RequestBody List<Long> disciplinasIds) {
        turmaService.removerDisciplina(id_turma, disciplinasIds);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id_turma}/notas/{id_disciplina}")
    public ResponseEntity<?> getNotasDosAlunosNaDisciplina(
            @PathVariable("id_turma") Long idTurma,
            @PathVariable("id_disciplina") Long idDisciplina) {
        List<Map<String, Object>> notasDosAlunos = turmaService.getNotasDosAlunosNaDisciplina(idTurma, idDisciplina);
        return ResponseEntity.ok(notasDosAlunos);
    }

    @PostMapping("/{idTurma}/disciplina/{idDisciplina}/notas")
    public ResponseEntity<?> cadastrarNota(@PathVariable Long idTurma, @PathVariable Long idDisciplina, @RequestBody Map<String, Object> requestBody) {
        try {
            Long idAluno = Long.parseLong(requestBody.get("idAluno").toString());
            Double notaProva1 = Double.parseDouble(requestBody.get("notaProva1").toString());
            Double notaProva2 = Double.parseDouble(requestBody.get("notaProva2").toString());
            Double notaProva3 = Double.parseDouble(requestBody.get("notaProva3").toString());
            Double notaTrabalho1 = Double.parseDouble(requestBody.get("notaTrabalho1").toString());
            Double notaTrabalho2 = Double.parseDouble(requestBody.get("notaTrabalho2").toString());
            Double notaTrabalho3 = Double.parseDouble(requestBody.get("notaTrabalho3").toString());
    
            notasService.cadastrarNota(idAluno, idTurma, idDisciplina, notaProva1, notaProva2, notaProva3, notaTrabalho1, notaTrabalho2, notaTrabalho3);
    
            return ResponseEntity.status(HttpStatus.CREATED).body("Notas cadastradas com sucesso.");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato inválido para ID do aluno ou notas.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    

}
