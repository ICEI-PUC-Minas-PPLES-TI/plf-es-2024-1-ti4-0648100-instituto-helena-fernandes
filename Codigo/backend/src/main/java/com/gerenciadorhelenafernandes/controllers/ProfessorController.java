package com.gerenciadorhelenafernandes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.models.Professor;
import com.gerenciadorhelenafernandes.services.ProfessorService;
import com.gerenciadorhelenafernandes.services.DisciplinaService;

import com.gerenciadorhelenafernandes.repositories.TurmaRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    private DisciplinaService disciplinaService;

    @Autowired
    private TurmaRepository turmaRepository;  

    @GetMapping("/{idProfessor}")
    public ResponseEntity<?> findById(@PathVariable("idProfessor") Long idProfessor) {
        return ResponseEntity.ok().body(professorService.findById(idProfessor));
    }

    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
        List<Professor> professores = professorService.findAll();
        return ResponseEntity.ok().body(professores);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> validation(@RequestBody Professor professor) {
        try {
            Long idProfessor = professorService.validateLogin(professor.getEmailProfessor(),
                    professor.getSenha_professor());
            return ResponseEntity.ok(idProfessor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1L); // Indica erro
        }
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
    @GetMapping("/{idProfessor}/turmas")
    public ResponseEntity<List<Turma>> findTurmasByProfessor(@PathVariable Long idProfessor) {
        try {
            List<Turma> turmas = turmaRepository.findByProfessorId(idProfessor);
            if (turmas.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(turmas);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception to the console or log file
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    


}
