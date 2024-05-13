package com.gerenciadorhelenafernandes.controllers;

import com.gerenciadorhelenafernandes.models.Notas;
import com.gerenciadorhelenafernandes.services.NotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class NotasController {

    private final NotasService notasService;

    @Autowired
    public NotasController(NotasService notasService) {
        this.notasService = notasService;
    }

    @GetMapping
    public ResponseEntity<List<Notas>> getAllNotas() {
        List<Notas> notasList = notasService.getAllNotas();
        return new ResponseEntity<>(notasList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notas> getNotasById(@PathVariable("id") Long id) {
        Optional<Notas> notas = notasService.getNotasById(id);
        return notas.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Notas> saveNotas(@RequestBody Notas notas) {
        Notas savedNotas = notasService.saveNotas(notas);
        return new ResponseEntity<>(savedNotas, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
public ResponseEntity<?> saveMultipleNotas(@RequestBody List<Notas> notasList) {
    for (Notas notas : notasList) {
        System.out.println("Recebido: " + notas);
    }
    notasService.saveAll(notasList);
    return ResponseEntity.status(HttpStatus.CREATED).build();
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotasById(@PathVariable("id") Long id) {
        notasService.deleteNotasById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
