package com.gerenciadorhelenafernandes.repositories;

import com.gerenciadorhelenafernandes.models.Professor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

        Optional<Professor> findByEmailProfessor(String emailProfessor);

}
