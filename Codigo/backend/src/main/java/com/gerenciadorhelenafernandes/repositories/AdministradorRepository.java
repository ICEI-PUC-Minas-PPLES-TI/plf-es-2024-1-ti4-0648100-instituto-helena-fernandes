package com.gerenciadorhelenafernandes.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gerenciadorhelenafernandes.models.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByEmailAdmin(String emailAdmin);
}
