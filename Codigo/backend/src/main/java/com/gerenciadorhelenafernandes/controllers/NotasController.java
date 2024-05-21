package com.gerenciadorhelenafernandes.controllers;

import com.gerenciadorhelenafernandes.models.Notas;
import com.gerenciadorhelenafernandes.services.NotasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@CrossOrigin("*")
public class NotasController {

    private final NotasService notasService;

    public NotasController(NotasService notasService) {
        this.notasService = notasService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notas> getNotasById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notasService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Notas>> listarTodas() {
        List<Notas> notasList = notasService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(notasList);
    }

    @PostMapping
    public ResponseEntity<?> saveMultipleNotas(@RequestBody List<Notas> notasList) {
        try {
            if (notasList == null || notasList.isEmpty()) {
                return ResponseEntity.badRequest().body("A lista de notas não pode estar vazia");
            }

            Notas primeiraNota = notasService.create(notasList.get(0));
            Long idNotas = primeiraNota.getIdNotas();

            for (Notas nota : notasList) {
                nota.setIdNotas(idNotas);
            }
            notasService.saveAll(notasList);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar múltiplas notas: " + e.getMessage());
        }
    }

    @PutMapping("/{id_notas}")
    public ResponseEntity<Notas> update(@RequestBody Notas notas, @PathVariable Long id_notas) {
        notas = notasService.update(id_notas, notas);
        return ResponseEntity.status(HttpStatus.OK).body(notas);
    }

    @DeleteMapping("/{id_notas}")
    public ResponseEntity<?> delete(@PathVariable Long id_notas) {
        notasService.delete(id_notas);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id_notas}/alunos")
    public ResponseEntity<?> adicionarAlunos(@PathVariable Long id_notas, @RequestBody List<Long> alunosIds) {
        notasService.adicionarAluno(id_notas, alunosIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id_notas}/alunos")
    public ResponseEntity<?> removerAlunos(@PathVariable Long id_notas, @RequestBody List<Long> alunosIds) {
        notasService.removerAluno(id_notas, alunosIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id_notas}/professores")
    public ResponseEntity<?> adicionarProfessores(@PathVariable Long id_notas, @RequestBody List<Long> professoresIds) {
        notasService.adicionarProfessor(id_notas, professoresIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id_notas}/professores")
    public ResponseEntity<?> removerProfessores(@PathVariable Long id_notas, @RequestBody List<Long> professoresIds) {
        notasService.removerProfessor(id_notas, professoresIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id_notas}/disciplinas")
    public ResponseEntity<?> adicionarDisciplinas(@PathVariable Long id_notas, @RequestBody List<Long> disciplinasIds) {
        notasService.adicionarDisciplina(id_notas, disciplinasIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id_notas}/disciplinas")
    public ResponseEntity<?> removerDisciplinas(@PathVariable Long id_notas, @RequestBody List<Long> disciplinasIds) {
        notasService.removerDisciplina(id_notas, disciplinasIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id_notas}/turmas")
    public ResponseEntity<?> adicionarTurmas(@PathVariable Long id_notas, @RequestBody List<Long> turmasIds) {
        notasService.adicionarTurma(id_notas, turmasIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id_notas}/turmas")
    public ResponseEntity<?> removerTurmas(@PathVariable Long id_notas, @RequestBody List<Long> turmasIds) {
        notasService.removerTurma(id_notas, turmasIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
