package com.gerenciadorhelenafernandes.repositories;

import com.gerenciadorhelenafernandes.models.Disciplina;
import com.gerenciadorhelenafernandes.models.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {


}
