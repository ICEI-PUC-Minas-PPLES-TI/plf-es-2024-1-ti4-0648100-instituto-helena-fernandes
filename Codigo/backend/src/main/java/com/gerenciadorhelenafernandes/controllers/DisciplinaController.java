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

import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.services.DisciplinaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/{id_disciplina}")
    public ResponseEntity<?> encontrarPorId(@PathVariable("id_disciplina") Long idDisciplina) {
        return ResponseEntity.status(200).body(disciplinaService.findById(idDisciplina));
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarTodas(
            @RequestParam(value = "nome", required = false) String nome) {
        List<Disciplina> lista;
        if (nome != null) {
            lista = disciplinaService.findByName(nome);
        } else {
            lista = disciplinaService.findAll();
        }
        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina) {
        disciplina = disciplinaService.create(disciplina);
        return ResponseEntity.status(201).body(disciplina);
    }

    @PutMapping("/{id_disciplina}")
    public ResponseEntity<Disciplina> update(@RequestBody Disciplina disciplina, @PathVariable Long id_disciplina) {
        disciplina.setIdDisciplina(id_disciplina); // Garante que o ID seja o mesmo informado na URL
        disciplina = disciplinaService.update(disciplina);
        return ResponseEntity.status(200).body(disciplina);
    }

    @DeleteMapping("/{id_disciplina}")
    public ResponseEntity<?> delete(@PathVariable Long id_disciplina) {
        disciplinaService.delete(id_disciplina);
        return ResponseEntity.status(204).build();
    }
}
