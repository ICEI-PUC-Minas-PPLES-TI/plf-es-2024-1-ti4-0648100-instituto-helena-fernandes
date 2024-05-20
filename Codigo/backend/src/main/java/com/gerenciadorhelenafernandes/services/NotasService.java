package com.gerenciadorhelenafernandes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorhelenafernandes.models.Notas;
import com.gerenciadorhelenafernandes.repositories.NotasRepository;

import jakarta.transaction.Transactional;

@Service
public class NotasService {

    @Autowired
    private NotasRepository notasRepository;

    public Notas findById(Long idNotas) {
        Optional<Notas> notasOptional = notasRepository.findById(idNotas);
        return notasOptional.orElseThrow(() -> new RuntimeException("Notas não encontrada! ID: " + idNotas));
    }

    public List<Notas> findAll() {
        return notasRepository.findAll();
    }

    @Transactional
    public Notas create(Notas notas) {
        notas.setIdNotas(null); // Garante que o ID seja nulo para evitar conflitos de atualização
        return notasRepository.save(notas);
    }

    @Transactional
    public Notas update(Notas notas) {
        Optional<Notas> notasOptional = notasRepository.findById(notas.getIdNotas());
        if (notasOptional.isPresent()) {
            return notasRepository.save(notas);
        } else {
            throw new RuntimeException("Notas não encontrada para atualização!");
        }
    }

    @Transactional
    public void delete(Long idNotas) {
        Optional<Notas> notasOptional = notasRepository.findById(idNotas);
        if (notasOptional.isPresent()) {
            notasRepository.delete(notasOptional.get());
        } else {
            throw new RuntimeException("Notas não encontrada para exclusão!");
        }
    }

    @Transactional
    public List<Notas> saveAll(List<Notas> notasList) {
        return notasRepository.saveAll(notasList);
    }
}
