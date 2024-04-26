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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            // Você pode implementar uma lógica de busca por nome se necessário
            lista = turmaService.findAll(); // Por enquanto, apenas retorna todas as turmas
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
}
