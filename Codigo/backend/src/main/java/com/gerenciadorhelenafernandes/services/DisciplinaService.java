package com.gerenciadorhelenafernandes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.repositories.DisciplinaRepository;

import jakarta.transaction.Transactional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina findById(Long idDisciplina) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(idDisciplina);
        return disciplinaOptional
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada! ID: " + idDisciplina));
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    @Transactional
    public Disciplina create(Disciplina disciplina) {
        // Garante que o ID seja nulo para evitar conflitos de atualização
        disciplina.setId_disciplina(null);
        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public Disciplina update(Disciplina disciplina) {
        // Verifica se a disciplina já existe no banco de dados
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(disciplina.getId_disciplina());
        if (disciplinaOptional.isPresent()) {
            return disciplinaRepository.save(disciplina); // Atualiza a disciplina existente
        } else {
            throw new RuntimeException("Disciplina não encontrada para atualização!");
        }
    }

    @Transactional
    public void delete(Long idDisciplina) {
        // Verifica se a disciplina existe no banco de dados antes de excluir
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(idDisciplina);
        if (disciplinaOptional.isPresent()) {
            disciplinaRepository.delete(disciplinaOptional.get());
        } else {
            throw new RuntimeException("Disciplina não encontrada para exclusão!");
        }
    }

    public List<Disciplina> findByName(String nome) {
        return disciplinaRepository.findByNomeDisciplina(nome);
    }

}
