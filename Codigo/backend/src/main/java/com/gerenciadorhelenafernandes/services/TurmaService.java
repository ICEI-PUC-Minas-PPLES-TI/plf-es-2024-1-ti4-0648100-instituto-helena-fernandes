package com.gerenciadorhelenafernandes.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gerenciadorhelenafernandes.models.Turma;
import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.models.Professor;
import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.models.Notas;
import com.gerenciadorhelenafernandes.repositories.TurmaRepository;
import com.gerenciadorhelenafernandes.repositories.AlunoRepository;
import com.gerenciadorhelenafernandes.repositories.ProfessorRepository;
import com.gerenciadorhelenafernandes.repositories.DisciplinaRepository;
import com.gerenciadorhelenafernandes.repositories.NotasRepository;

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

    @Autowired
    private NotasRepository notasRepository;

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
        turma.setIdTurma(null);
        return turmaRepository.save(turma);
    }

    @Transactional
    public Turma update(Long idTurma, Turma turmaAtualizada) {
        Turma turmaExistente = findById(idTurma);

        // Atualizar os campos da turma com base nos dados recebidos
        turmaExistente.setNome_turma(turmaAtualizada.getNome_turma());

        // Atualizar relacionamentos com alunos
        List<Aluno> alunosSelecionados = alunoRepository
                .findAllById(turmaAtualizada.getAlunos().stream().map(Aluno::getIdAluno).collect(Collectors.toList()));
        turmaExistente.setAlunos(alunosSelecionados);

        // Atualizar relacionamentos com professores
        List<Professor> professoresSelecionados = professorRepository.findAllById(
                turmaAtualizada.getProfessores().stream().map(Professor::getId_professor).collect(Collectors.toList()));
        turmaExistente.setProfessores(professoresSelecionados);

        // Atualizar relacionamentos com disciplinas
        List<Disciplina> disciplinasSelecionadas = disciplinaRepository.findAllById(turmaAtualizada.getDisciplinas()
                .stream().map(Disciplina::getIdDisciplina).collect(Collectors.toList()));
        turmaExistente.setDisciplinas(disciplinasSelecionadas);

        // Salvar e retornar a turma atualizada
        return turmaRepository.save(turmaExistente);
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
    public List<Professor> getProfessoresById(Long id_turma) {
        Turma turma = turmaRepository.findById(id_turma).orElseThrow(() -> new RuntimeException("Turma não encontrada!"));
        return turma.getProfessores();
    }

    @Transactional
    public List<Disciplina> getDisciplinasById(Long id_turma) {
        Turma turma = turmaRepository.findById(id_turma).orElseThrow(() -> new RuntimeException("Turma não encontrada!"));
        return turma.getDisciplinas();
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


    @Transactional
    public List<Map<String, Object>> getNotasDosAlunosNaDisciplina(Long idTurma, Long idDisciplina) {
        List<Map<String, Object>> notasDosAlunos = new ArrayList<>();
    
        // Buscar a turma pelo ID
        Turma turma = turmaRepository.findById(idTurma).orElse(null);
    
        // Verificar se a turma existe
        if (turma != null) {
            // Buscar a disciplina pelo ID
            Disciplina disciplina = disciplinaRepository.findById(idDisciplina).orElse(null);
    
            // Verificar se a disciplina existe
            if (disciplina != null) {
                // Iterar sobre os alunos da turma
                for (Aluno aluno : turma.getAlunos()) {
                    Map<String, Object> notasDoAluno = new HashMap<>();
                    notasDoAluno.put("idAluno", aluno.getIdAluno());
                    notasDoAluno.put("nomeAluno", aluno.getNome_aluno());
    
                    // Buscar as notas do aluno na disciplina específica
                    List<Notas> notasList = notasRepository.findByAlunosIdAlunoAndTurmasIdTurmaAndDisciplinasIdDisciplina(
                            aluno.getIdAluno(), idTurma, idDisciplina);
    
                    // Verificar se existem notas para o aluno na disciplina
                    if (notasList != null && !notasList.isEmpty()) {
                        Notas notas = notasList.get(0); // Supondo que sempre haverá uma única nota relevante
                        // Adicionar as notas encontradas
                        notasDoAluno.put("idNota", notas.getIdNotas()); // Incluir o idNota
                        notasDoAluno.put("notaProva1", notas.getProva1());
                        notasDoAluno.put("notaProva2", notas.getProva2());
                        notasDoAluno.put("notaProva3", notas.getProva3());
    
                        notasDoAluno.put("notaTrabalho1", notas.getTrabalho1());
                        notasDoAluno.put("notaTrabalho2", notas.getTrabalho2());
                        notasDoAluno.put("notaTrabalho3", notas.getTrabalho3());
                    } else {
                        // Caso não existam notas para o aluno na disciplina, adicione valores padrão
                        notasDoAluno.put("idNota", null); // Incluir idNota como null
                        notasDoAluno.put("notaProva1", 0);
                        notasDoAluno.put("notaProva2", 0);
                        notasDoAluno.put("notaProva3", 0);
    
                        notasDoAluno.put("notaTrabalho1", 0);
                        notasDoAluno.put("notaTrabalho2", 0);
                        notasDoAluno.put("notaTrabalho3", 0);
                    }
    
                    // Adicionar as notas do aluno na lista
                    notasDosAlunos.add(notasDoAluno);
                }
            }
        }
    
        return notasDosAlunos;
    }
    
    
    @Transactional
    public Map<String, Object> getNotasDoAlunoNaDisciplina(Long idTurma, Long idDisciplina, Long idAluno) {
        Map<String, Object> notasDoAluno = new HashMap<>();
    
        // Buscar a turma pelo ID
        Turma turma = turmaRepository.findById(idTurma).orElse(null);
    
        // Verificar se a turma existe
        if (turma != null) {
            // Buscar a disciplina pelo ID
            Disciplina disciplina = disciplinaRepository.findById(idDisciplina).orElse(null);
    
            // Verificar se a disciplina existe
            if (disciplina != null) {
                // Buscar o aluno pelo ID
                Aluno aluno = alunoRepository.findById(idAluno).orElse(null);
    
                // Verificar se o aluno existe e se está matriculado na turma
                if (aluno != null && turma.getAlunos().contains(aluno)) {
                    notasDoAluno.put("idAluno", aluno.getIdAluno());
                    notasDoAluno.put("nomeAluno", aluno.getNome_aluno());
    
                    // Buscar as notas do aluno na disciplina específica
                    List<Notas> notasList = notasRepository.findByAlunosIdAlunoAndTurmasIdTurmaAndDisciplinasIdDisciplina(
                            idAluno, idTurma, idDisciplina);
    
                    // Verificar se existem notas para o aluno na disciplina
                    if (notasList != null && !notasList.isEmpty()) {
                        Notas notas = notasList.get(0); // Supondo que sempre haverá uma única nota relevante
                        // Adicionar as notas encontradas
                        notasDoAluno.put("notaProva1", notas.getProva1());
                        notasDoAluno.put("notaProva2", notas.getProva2());
                        notasDoAluno.put("notaProva3", notas.getProva3());
    
                        notasDoAluno.put("notaTrabalho1", notas.getTrabalho1());
                        notasDoAluno.put("notaTrabalho2", notas.getTrabalho2());
                        notasDoAluno.put("notaTrabalho3", notas.getTrabalho3());
                    } else {
                        // Caso não existam notas para o aluno na disciplina, adicione valores padrão
                        notasDoAluno.put("notaProva1", 0);
                        notasDoAluno.put("notaProva2", 0);
                        notasDoAluno.put("notaProva3", 0);
    
                        notasDoAluno.put("notaTrabalho1", 0);
                        notasDoAluno.put("notaTrabalho2", 0);
                        notasDoAluno.put("notaTrabalho3", 0);
                    }
                }
            }
        }
    
        return notasDoAluno;
    }
}
