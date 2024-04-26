package com.gerenciadorhelenafernandes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorhelenafernandes.models.Professor;
import com.gerenciadorhelenafernandes.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor findById(Long idProfessor) {
        Optional<Professor> professorOptional = professorRepository.findById(idProfessor);
        return professorOptional.orElseThrow(() -> new RuntimeException("Professor não encontrado! ID: " + idProfessor));
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Transactional
    public Professor create(Professor professor) {
        // Garante que o ID seja nulo para evitar conflitos de atualização
        professor.setId_professor(null);
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor update(Professor professor) {
        // Verifica se o professor já existe no banco de dados
        Optional<Professor> professorOptional = professorRepository.findById(professor.getId_professor());
        if (professorOptional.isPresent()) {
            return professorRepository.save(professor); // Atualiza o professor existente
        } else {
            throw new RuntimeException("Professor não encontrado para atualização!");
        }
    }

    @Transactional
    public void delete(Long idProfessor) {
        // Verifica se o professor existe no banco de dados antes de excluir
        Optional<Professor> professorOptional = professorRepository.findById(idProfessor);
        if (professorOptional.isPresent()) {
            professorRepository.delete(professorOptional.get());
        } else {
            throw new RuntimeException("Professor não encontrado para exclusão!");
        }
    }
}
