package com.gerenciadorhelenafernandes.services;

import java.util.Collections;
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

    public Notas findNotasByAlunoIdAndTurmaId(Long idAluno, Long idTurma) {
        return notasRepository.findByAlunosIdAlunoAndTurmasIdTurma(idAluno, idTurma);
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
    public void saveMultipleNotas(List<Notas> notasList) {
        for (Notas nota : notasList) {
            // Fetch and set persistent entities for each nota
            List<Aluno> alunosPersistentes = alunoRepository.findAllById(
                    nota.getAlunos().stream().map(Aluno::getIdAluno).collect(Collectors.toList()));

            List<Professor> professoresPersistentes = professorRepository.findAllById(
                    nota.getProfessores().stream().map(Professor::getId_professor).collect(Collectors.toList()));

            List<Disciplina> disciplinasPersistentes = disciplinaRepository.findAllById(
                    nota.getDisciplinas().stream().map(Disciplina::getIdDisciplina).collect(Collectors.toList()));

            List<Turma> turmasPersistentes = turmaRepository.findAllById(
                    nota.getTurmas().stream().map(Turma::getIdTurma).collect(Collectors.toList()));

            nota.setAlunos(alunosPersistentes);
            nota.setProfessores(professoresPersistentes);
            nota.setDisciplinas(disciplinasPersistentes);
            nota.setTurmas(turmasPersistentes);
        }

        notasRepository.saveAll(notasList);
    }

    @Transactional
    public Notas update(Long idNotas, Notas notasAtualizada) {
        Notas notasExistente = findById(idNotas);

        // Atualizar apenas as notas
        notasExistente.setProva1(notasAtualizada.getProva1());
        notasExistente.setProva2(notasAtualizada.getProva2());
        notasExistente.setProva3(notasAtualizada.getProva3());
        notasExistente.setTrabalho1(notasAtualizada.getTrabalho1());
        notasExistente.setTrabalho2(notasAtualizada.getTrabalho2());
        notasExistente.setTrabalho3(notasAtualizada.getTrabalho3());

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
    public Notas cadastrarNota(Long idAluno,Long idTurma, Long idDisciplina,  Long idProfessor, Double notaProva1, Double notaProva2,
            Double notaProva3, Double notaTrabalho1, Double notaTrabalho2, Double notaTrabalho3) {
        // Buscar aluno, turma e disciplina pelo ID
        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
        Turma turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada!"));
        Professor professor = professorRepository.findById(idProfessor)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado!"));
        Disciplina disciplina = disciplinaRepository.findById(idDisciplina)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada!"));

        // Criar uma nova instância de Notas
        Notas novaNota = new Notas();
        novaNota.setAlunos(Collections.singletonList(aluno));
        novaNota.setTurmas(Collections.singletonList(turma));        
        novaNota.setProfessores(Collections.singletonList(professor));
        novaNota.setDisciplinas(Collections.singletonList(disciplina));
        novaNota.setProva1(notaProva1);
        novaNota.setProva2(notaProva2);
        novaNota.setProva3(notaProva3);
        novaNota.setTrabalho1(notaTrabalho1);
        novaNota.setTrabalho2(notaTrabalho2);
        novaNota.setTrabalho3(notaTrabalho3);

        // Salvar e retornar a nova nota
        return notasRepository.save(novaNota);
    }

    @Transactional
    public void editarNota(Long idNota, Double notaProva1, Double notaProva2,
            Double notaProva3, Double notaTrabalho1, Double notaTrabalho2, Double notaTrabalho3) {
        Notas notas = findById(idNota);

        notas.setProva1(notaProva1);
        notas.setProva2(notaProva2);
        notas.setProva3(notaProva3);
        notas.setTrabalho1(notaTrabalho1);
        notas.setTrabalho2(notaTrabalho2);
        notas.setTrabalho3(notaTrabalho3);

        notasRepository.save(notas);
    }

}
