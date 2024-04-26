package com.gerenciadorhelenafernandes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.models.Professor;
import com.gerenciadorhelenafernandes.services.ProfessorService;
import com.gerenciadorhelenafernandes.services.DisciplinaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    private DisciplinaService disciplinaService;

    @GetMapping("/{idProfessor}")
    public ResponseEntity<?> findById(@PathVariable("idProfessor") Long idProfessor) {
        return ResponseEntity.ok().body(professorService.findById(idProfessor));
    }

    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
        List<Professor> professores = professorService.findAll();
        return ResponseEntity.ok().body(professores);
    }

    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> findAllDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.findAll();
        return ResponseEntity.ok().body(disciplinas);
    }

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        Professor novoProfessor = professorService.create(professor);
        return ResponseEntity.status(201).body(novoProfessor);
    }

    @PutMapping("/{idProfessor}")
    public ResponseEntity<Professor> update(@RequestBody Professor professor, @PathVariable Long idProfessor) {
        professor.setId_professor(idProfessor); // Garante que o ID seja o mesmo informado na URL
        Professor professorAtualizado = professorService.update(professor);
        return ResponseEntity.ok().body(professorAtualizado);
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<?> delete(@PathVariable Long idProfessor) {
        professorService.delete(idProfessor);
        return ResponseEntity.noContent().build();
    }
}
