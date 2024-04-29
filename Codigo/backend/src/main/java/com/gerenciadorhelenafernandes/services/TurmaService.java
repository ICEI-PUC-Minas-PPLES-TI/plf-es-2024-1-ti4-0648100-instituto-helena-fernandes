package com.gerenciadorhelenafernandes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gerenciadorhelenafernandes.models.Turma;
import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.models.Professor;
import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.repositories.TurmaRepository;
import com.gerenciadorhelenafernandes.repositories.AlunoRepository;
import com.gerenciadorhelenafernandes.repositories.ProfessorRepository;
import com.gerenciadorhelenafernandes.repositories.DisciplinaRepository;

import jakarta.transaction.Transactional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

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
            // Exclui a turma, o que deve lidar com a exclusão das entradas relacionadas nas tabelas turma_aluno, turma_professor e turma_disciplina
            turmaRepository.delete(turmaOptional.get());
        } else {
            throw new RuntimeException("Turma não encontrada para exclusão!");
        }
    }

    @Transactional
    public void adicionarAluno(Long idTurma, List<Long> alunosIds) {
        Turma turma = findById(idTurma);
        List<Aluno> alunos = alunoRepository.findAllById(alunosIds);
        turma.getAlunos().addAll(alunos);
    }

    @Transactional
    public void removerAluno(Long idTurma, List<Long> alunosIds) {
        Turma turma = findById(idTurma);
        List<Aluno> alunos = alunoRepository.findAllById(alunosIds);
        turma.getAlunos().removeAll(alunos);
    }

    @Transactional
    public void adicionarProfessor(Long idTurma, List<Long> professoresIds) {
        Turma turma = findById(idTurma);
        List<Professor> professores = professorRepository.findAllById(professoresIds);
        turma.getProfessores().addAll(professores);
    }

    @Transactional
    public void removerProfessor(Long idTurma, List<Long> professoresIds) {
        Turma turma = findById(idTurma);
        List<Professor> professores = professorRepository.findAllById(professoresIds);
        turma.getProfessores().removeAll(professores);
    }

    @Transactional
    public void adicionarDisciplina(Long idTurma, List<Long> disciplinasIds) {
        Turma turma = findById(idTurma);
        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinasIds);
        turma.getDisciplinas().addAll(disciplinas);
    }

    @Transactional
    public void removerDisciplina(Long idTurma, List<Long> disciplinasIds) {
        Turma turma = findById(idTurma);
        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinasIds);
        turma.getDisciplinas().removeAll(disciplinas);
    }
}
