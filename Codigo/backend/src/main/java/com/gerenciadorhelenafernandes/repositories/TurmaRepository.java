package com.gerenciadorhelenafernandes.repositories;

import com.gerenciadorhelenafernandes.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query("SELECT t FROM Turma t JOIN t.professores p WHERE p.id_professor = :idProfessor")
    List<Turma> findByProfessorId(Long idProfessor);
}
