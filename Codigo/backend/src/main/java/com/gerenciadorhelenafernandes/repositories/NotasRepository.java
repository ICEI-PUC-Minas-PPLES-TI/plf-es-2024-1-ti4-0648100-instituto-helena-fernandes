package com.gerenciadorhelenafernandes.repositories;

import com.gerenciadorhelenafernandes.models.Notas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {

    Notas findByAlunosIdAlunoAndTurmasIdTurma(Long idAluno, Long idTurma);

    List<Notas> findByAlunosIdAlunoAndTurmasIdTurmaAndDisciplinasIdDisciplina(Long alunoId, Long turmaId, Long disciplinaId);

}
