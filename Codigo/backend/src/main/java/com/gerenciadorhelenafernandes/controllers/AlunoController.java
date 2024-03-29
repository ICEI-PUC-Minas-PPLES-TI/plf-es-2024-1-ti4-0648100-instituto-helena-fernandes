package com.gerenciadorhelenafernandes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.repositories.AlunoRepository;
import com.gerenciadorhelenafernandes.services.AlunoServices;


@RestController
@CrossOrigin("*")
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoServices alunoService;

    @GetMapping("/{id_aluno}")
    public ResponseEntity<?> findById(@PathVariable("id_aluno") Long id_aluno) {
        return ResponseEntity.status(200).body(alunoService.findById(id_aluno));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> list = alunoService.findAll();
        return ResponseEntity.status(200).body(list);
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        aluno = alunoService.create(aluno);
        return ResponseEntity.status(201).body(aluno);
    }

    @PutMapping("/{id_aluno}")
    public ResponseEntity<Aluno> update(@RequestBody Aluno aluno, @PathVariable Long id_aluno) {
        aluno = alunoService.update(aluno, id_aluno);
        return ResponseEntity.status(201).body(aluno);
    }

    @DeleteMapping("/{id_aluno}")
    public ResponseEntity<?> delete(@PathVariable Long id_aluno) {
        alunoService.delete(id_aluno);
        return ResponseEntity.status(204).build();
    }

        @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
}
