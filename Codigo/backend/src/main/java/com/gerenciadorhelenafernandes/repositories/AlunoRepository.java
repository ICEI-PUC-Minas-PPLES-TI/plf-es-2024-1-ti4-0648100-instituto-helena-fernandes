package com.gerenciadorhelenafernandes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciadorhelenafernandes.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

    Optional<Aluno> findByEmailAluno(String emailAluno);


}