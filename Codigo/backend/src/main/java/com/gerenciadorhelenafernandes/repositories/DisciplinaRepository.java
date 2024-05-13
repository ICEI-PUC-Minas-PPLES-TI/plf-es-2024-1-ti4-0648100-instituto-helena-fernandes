package com.gerenciadorhelenafernandes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciadorhelenafernandes.models.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{

    List<Disciplina> findByNomeDisciplina(String nome);

}

