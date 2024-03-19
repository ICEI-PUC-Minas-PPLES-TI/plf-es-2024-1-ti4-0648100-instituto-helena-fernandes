package com.gerenciadorhelenafernandes.controllers;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.services.AlunoServices;

@RestController
@CrossOrigin("*")
@RequestMapping("/aluno")
public class AlunoController {
    
    @Autowired
    private AlunoServices alunoService;
    
    @GetMapping(path = {"/id"})
    public ResponseEntity<?> findById(@PathVariable Long idAluno){
     return ResponseEntity.ok().body(alunoService.findById(idAluno));
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
    aluno = alunoService.create(aluno);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(aluno.getIdAluno()).toUri();
    return ResponseEntity.ok().body(aluno);
    }

     @PutMapping("/aluno/{idAluno}")
    public ResponseEntity<Aluno> update(@RequestBody Aluno aluno, @PathVariable Long idAluno){
        aluno = alunoService.update(aluno, idAluno);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long idAluno){
        alunoService.delete(idAluno);
        return ResponseEntity.noContent().build();
    }



}
