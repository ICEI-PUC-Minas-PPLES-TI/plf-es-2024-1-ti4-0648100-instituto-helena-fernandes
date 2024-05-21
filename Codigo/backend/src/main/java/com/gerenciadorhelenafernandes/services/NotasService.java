package com.gerenciadorhelenafernandes.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.models.Notas;
import com.gerenciadorhelenafernandes.models.Professor;
import com.gerenciadorhelenafernandes.models.Turma;
import com.gerenciadorhelenafernandes.repositories.AlunoRepository;
import com.gerenciadorhelenafernandes.repositories.DisciplinaRepository;
import com.gerenciadorhelenafernandes.repositories.NotasRepository;
import com.gerenciadorhelenafernandes.repositories.ProfessorRepository;
import com.gerenciadorhelenafernandes.repositories.TurmaRepository;

import jakarta.transaction.Transactional;

@Service
public class NotasService {

    @Autowired
    private NotasRepository notasRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

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
    public Notas update(Long idNotas, Notas notasAtualizada) {
        Notas notasExistente = findById(idNotas);
        
        // Atualizar campos básicos
        notasExistente.setProva1(notasAtualizada.getProva1());
        notasExistente.setProva2(notasAtualizada.getProva2());
        notasExistente.setProva3(notasAtualizada.getProva3());
        notasExistente.setTrabalho1(notasAtualizada.getTrabalho1());
        notasExistente.setTrabalho2(notasAtualizada.getTrabalho2());
        notasExistente.setTrabalho3(notasAtualizada.getTrabalho3());

        // Atualizar relacionamentos com alunos
        List<Aluno> alunosSelecionados = alunoRepository.findAllById(notasAtualizada.getAlunos().stream().map(Aluno::getId_aluno).collect(Collectors.toList()));
        notasExistente.setAlunos(alunosSelecionados);

        // Atualizar relacionamentos com professores
        List<Professor> professoresSelecionados = professorRepository.findAllById(notasAtualizada.getProfessores().stream().map(Professor::getId_professor).collect(Collectors.toList()));
        notasExistente.setProfessores(professoresSelecionados);

        // Atualizar relacionamentos com disciplinas
        List<Disciplina> disciplinasSelecionadas = disciplinaRepository.findAllById(notasAtualizada.getDisciplinas().stream().map(Disciplina::getIdDisciplina).collect(Collectors.toList()));
        notasExistente.setDisciplinas(disciplinasSelecionadas);

        // Atualizar relacionamentos com turmas
        List<Turma> turmasSelecionadas = turmaRepository.findAllById(notasAtualizada.getTurmas().stream().map(Turma::getId_turma).collect(Collectors.toList()));
        notasExistente.setTurmas(turmasSelecionadas);

        return notasRepository.save(notasExistente);
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

    @Transactional
    public void adicionarAluno(Long idNotas, List<Long> alunosIds) {
        Notas notas = findById(idNotas);
        List<Aluno> alunos = alunoRepository.findAllById(alunosIds);
        notas.getAlunos().addAll(alunos);
    }

    @Transactional
    public void removerAluno(Long idNotas, List<Long> alunosIds) {
        Notas notas = findById(idNotas);
        List<Aluno> alunos = alunoRepository.findAllById(alunosIds);
        notas.getAlunos().removeAll(alunos);
    }

    @Transactional
    public void adicionarProfessor(Long idNotas, List<Long> professoresIds) {
        Notas notas = findById(idNotas);
        List<Professor> professores = professorRepository.findAllById(professoresIds);
        notas.getProfessores().addAll(professores);
    }

    @Transactional
    public void removerProfessor(Long idNotas, List<Long> professoresIds) {
        Notas notas = findById(idNotas);
        List<Professor> professores = professorRepository.findAllById(professoresIds);
        notas.getProfessores().removeAll(professores);
    }

    @Transactional
    public void adicionarDisciplina(Long idNotas, List<Long> disciplinasIds) {
        Notas notas = findById(idNotas);
        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinasIds);
        notas.getDisciplinas().addAll(disciplinas);
    }

    @Transactional
    public void removerDisciplina(Long idNotas, List<Long> disciplinasIds) {
        Notas notas = findById(idNotas);
        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinasIds);
        notas.getDisciplinas().removeAll(disciplinas);
    }

    @Transactional
    public void adicionarTurma(Long idNotas, List<Long> turmasIds) {
        Notas notas = findById(idNotas);
        List<Turma> turmas = turmaRepository.findAllById(turmasIds);
        notas.getTurmas().addAll(turmas);
    }

    @Transactional
    public void removerTurma(Long idNotas, List<Long> turmasIds) {
        Notas notas = findById(idNotas);
        List<Turma> turmas = turmaRepository.findAllById(turmasIds);
        notas.getTurmas().removeAll(turmas);
    }
}
