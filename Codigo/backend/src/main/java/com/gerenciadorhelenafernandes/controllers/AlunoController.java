package com.gerenciadorhelenafernandes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.gerenciadorhelenafernandes.models.Aluno;
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

    /*@GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> list = alunoService.findAll();
        return ResponseEntity.status(200).body(list);
    }*/
    @GetMapping
public ResponseEntity<List<Aluno>> findAll(@RequestParam(value = "status_aluno", required = false) String status_aluno) {
    List<Aluno> list;
    if (status_aluno != null) {
        list = alunoService.findByStatus(status_aluno);
    } else {
        list = alunoService.findAll();
    }
    return ResponseEntity.status(200).body(list);
}


    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        aluno = alunoService.create(aluno);
        return ResponseEntity.status(201).body(aluno);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> validation(@RequestBody Aluno aluno) {
        try {
            Boolean valid = alunoService.validateLogin(aluno.getEmailAluno(), aluno.getSenha_aluno());
            if (valid) {
                Long idAluno = alunoService.findIdByEmail(aluno.getEmailAluno());
                return ResponseEntity.ok(idAluno);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(-1L); // Retorna -1 para indicar login inválido
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1L); // Retorna -1 para indicar erro
        }
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
}
