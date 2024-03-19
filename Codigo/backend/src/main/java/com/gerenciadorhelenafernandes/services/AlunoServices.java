package com.gerenciadorhelenafernandes.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.repositories.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class AlunoServices {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findById(Long id){
        Optional<Aluno> obj = this.alunoRepository.findById(id);
        return obj.orElseThrow(()-> new RuntimeException(
            "Aluno não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()
        ));
    }

    //Salvar no Banco de Dados 
    @Transactional
    public Aluno create(Aluno obj){
        obj.setIdAluno(null);
        return this.alunoRepository.save(obj);

    }

    public Aluno update(Aluno obj, Long idAluno){
        obj.setIdAluno(idAluno);
        return alunoRepository.save(obj);
    }

    public void delete(Long idAluno){
    Aluno aluno = findById(idAluno);
    try {
        this.alunoRepository.delete(aluno);
    } catch (Exception e) {
        throw new RuntimeException("Não foi possível excluir pois há entidades relacionadas!");
    }
    }
}