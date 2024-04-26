package com.gerenciadorhelenafernandes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gerenciadorhelenafernandes.models.Turma;
import com.gerenciadorhelenafernandes.repositories.TurmaRepository;

import jakarta.transaction.Transactional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma findById(Long idTurma) {
        Optional<Turma> turmaOptional = turmaRepository.findById(idTurma);
        return turmaOptional.orElseThrow(() -> new RuntimeException("Turma não encontrada! ID: " + idTurma));
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    @Transactional
    public Turma create(Turma turma) {
        // Garante que o ID seja nulo para evitar conflitos de atualização
        turma.setId_turma(null);
        return turmaRepository.save(turma);
    }

    @Transactional
    public Turma update(Turma turma) {
        // Verifica se a turma já existe no banco de dados
        Optional<Turma> turmaOptional = turmaRepository.findById(turma.getId_turma());
        if (turmaOptional.isPresent()) {
            return turmaRepository.save(turma); // Atualiza a turma existente
        } else {
            throw new RuntimeException("Turma não encontrada para atualização!");
        }
    }

    @Transactional
    public void delete(Long idTurma) {
        // Verifica se a turma existe no banco de dados antes de excluir
        Optional<Turma> turmaOptional = turmaRepository.findById(idTurma);
        if (turmaOptional.isPresent()) {
            turmaRepository.delete(turmaOptional.get());
        } else {
            throw new RuntimeException("Turma não encontrada para exclusão!");
        }
    }

}
