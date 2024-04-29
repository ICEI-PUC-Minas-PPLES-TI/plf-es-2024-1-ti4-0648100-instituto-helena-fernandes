package com.gerenciadorhelenafernandes.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gerenciadorhelenafernandes.models.Turma;
import com.gerenciadorhelenafernandes.services.TurmaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/{id_turma}")
    public ResponseEntity<?> findById(@PathVariable("id_turma") Long idTurma) {
        return ResponseEntity.status(200).body(turmaService.findById(idTurma));
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
    public ResponseEntity<Turma> update(@RequestBody Turma turma, @PathVariable Long id_turma) {
        turma.setId_turma(id_turma); // Garante que o ID seja o mesmo informado na URL
        turma = turmaService.update(turma);
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
}
