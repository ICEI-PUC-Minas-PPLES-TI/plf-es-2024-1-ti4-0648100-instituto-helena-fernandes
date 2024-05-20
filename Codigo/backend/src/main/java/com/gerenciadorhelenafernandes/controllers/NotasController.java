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
    public ResponseEntity<Notas> create(@RequestBody Notas notas) {
        notas = notasService.create(notas);
        return ResponseEntity.status(HttpStatus.CREATED).body(notas);
    }

@PostMapping("/multiple")
public ResponseEntity<?> saveMultipleNotas(@RequestBody List<Notas> notasList) {
    try {
        if (notasList == null || notasList.isEmpty()) {
            return ResponseEntity.badRequest().body("A lista de notas não pode estar vazia");
        }

        System.out.println("Recebendo notas para salvar: " + notasList);

        Notas primeiraNota = notasService.create(notasList.get(0));
        Long idNotas = primeiraNota.getIdNotas();

        for (Notas nota : notasList) {
            nota.setIdNotas(idNotas);
        }
        notasService.saveAll(notasList);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar múltiplas notas: " + e.getMessage());
    }
}

    @PutMapping("/{id_notas}")
    public ResponseEntity<Notas> update(@RequestBody Notas notas, @PathVariable Long id_notas) {
        notas.setIdNotas(id_notas);
        notas = notasService.update(notas);
        return ResponseEntity.status(HttpStatus.OK).body(notas);
    }

    @DeleteMapping("/{id_notas}")
    public ResponseEntity<?> delete(@PathVariable Long id_notas) {
        notasService.delete(id_notas);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
