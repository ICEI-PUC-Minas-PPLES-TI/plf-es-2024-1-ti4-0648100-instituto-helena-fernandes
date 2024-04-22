package com.gerenciadorhelenafernandes.controllers;

import com.gerenciadorhelenafernandes.models.Professor;
import com.gerenciadorhelenafernandes.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "*") // Permite requisições CORS deste origin específico
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> getAllProfessores() {
        return professorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        return professorService.findById(id)
                .map(professor -> ResponseEntity.ok().body(professor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.save(professor);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professorDetails) {
        return professorService.findById(id)
                .map(existingProfessor -> {
                    existingProfessor.setNome_professor(professorDetails.getNome_professor());
                    existingProfessor.setCpf_professor(professorDetails.getCpf_professor());
                    existingProfessor.setData_nascimento(professorDetails.getData_nascimento());
                    existingProfessor.setFormacao_professor(professorDetails.getFormacao_professor());
                    existingProfessor.setDisciplina_professor(professorDetails.getDisciplina_professor());
                    existingProfessor.setEmail_professor(professorDetails.getEmail_professor());
                    Professor updatedProfessor = professorService.save(existingProfessor);
                    return ResponseEntity.ok().body(updatedProfessor);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        return professorService.findById(id)
                .map(professor -> {
                    professorService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
