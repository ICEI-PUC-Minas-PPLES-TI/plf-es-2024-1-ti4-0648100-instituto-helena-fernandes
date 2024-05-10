package com.gerenciadorhelenafernandes.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gerenciadorhelenafernandes.models.Administrador;
import com.gerenciadorhelenafernandes.repositories.AdministradorRepository;

import jakarta.transaction.Transactional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

        public Administrador findById(Long id) {
        Optional<Administrador> administrador = this.administradorRepository.findById(id);
        return administrador.orElseThrow(() -> new RuntimeException(
                "Administrador não encontrado! Id: " + id + ", Tipo: " + Administrador.class.getName()));
    }

    @Transactional
    public Administrador create(Administrador obj) {
        obj.setId_administrador(null);
        return this.administradorRepository.save(obj);
    }

    public Long validateLogin(String email, String senha) {
        Optional<Administrador> adminOptional = administradorRepository.findByEmailAdmin(email);
        if (adminOptional.isPresent()) {
            Administrador admin = adminOptional.get();
            if (senha.equals(admin.getSenha_admin())) {
                return admin.getId_administrador(); // Login bem-sucedido
            }
        }
        throw new RuntimeException("Administrador não encontrado ou senha inválida.");
    }
}
