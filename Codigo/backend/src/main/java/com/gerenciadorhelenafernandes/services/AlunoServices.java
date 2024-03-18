package com.gerenciadorhelenafernandes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.repositories.AlunoRepository;

@Service
public class AlunoServices {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findById(Long id){
        Optional<Aluno>aluno = this.alunoRepository.findById(id);
        return aluno.orElseThrow(()-> new RuntimeException(
            "Aluno não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()
        ));
    }
}
