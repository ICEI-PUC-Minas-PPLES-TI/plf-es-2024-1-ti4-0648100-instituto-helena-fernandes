package com.gerenciadorhelenafernandes.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerenciadorhelenafernandes.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

    Optional<Aluno> findByEmailAluno(String emailAluno);

   @Query("SELECT a FROM Aluno a WHERE a.status_aluno = :statusAluno") // Seleciona todos os alunos com o status especificado no arquivo ListaAlunos.js
    List<Aluno> findByStatusAluno(@Param("statusAluno") String statusAluno);


}
