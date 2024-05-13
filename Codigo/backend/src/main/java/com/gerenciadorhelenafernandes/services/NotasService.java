package com.gerenciadorhelenafernandes.services;

import com.gerenciadorhelenafernandes.models.Notas;
import com.gerenciadorhelenafernandes.repositories.NotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotasService {

    private final NotasRepository notasRepository;

    @Autowired
    public NotasService(NotasRepository notasRepository) {
        this.notasRepository = notasRepository;
    }

    public List<Notas> getAllNotas() {
        return notasRepository.findAll();
    }

    public Optional<Notas> getNotasById(Long id) {
        return notasRepository.findById(id);
    }

    public Notas saveNotas(Notas notas) {
        return notasRepository.save(notas);
    }

    public void deleteNotasById(Long id) {
        notasRepository.deleteById(id);
    }
    public List<Notas> saveAll(List<Notas> notasList) {
        return notasRepository.saveAll(notasList);
    }
}
