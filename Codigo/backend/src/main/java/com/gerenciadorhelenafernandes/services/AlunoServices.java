package com.gerenciadorhelenafernandes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gerenciadorhelenafernandes.models.Aluno;
import com.gerenciadorhelenafernandes.repositories.AlunoRepository;

import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
public class AlunoServices {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Aluno findById(@NonNull Long id) {
        Optional<Aluno> obj = this.alunoRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException(
                "Aluno não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
    }

    // Salvar no Banco de Dados
    @Transactional
    public Aluno create(Aluno obj) {
        String encoder = this.passwordEncoder.encode(obj.getSenha_aluno());
        obj.setSenha_aluno(encoder);
        obj.setIdAluno(null);
        return this.alunoRepository.save(obj);
    }

    public Aluno update(Aluno obj, Long id) {
        Aluno aluno = findById(obj.getIdAluno());
        String encoder = this.passwordEncoder.encode(obj.getSenha_aluno());
        aluno.setEmail_aluno(obj.getEmail_aluno());
        aluno.setSenha_aluno(encoder);
        aluno.setNome_aluno(obj.getNome_aluno());
        aluno.setData_nascimento(obj.getData_nascimento());
        aluno.setAlergia(obj.getAlergia());
        aluno.setCidade(obj.getCidade());
        aluno.setRua(obj.getRua());
        aluno.setNumero_casa(obj.getNumero_casa());
        aluno.setMais_informacoes(obj.getMais_informacoes());
        aluno.setNome_responsavel(obj.getNome_responsavel());
        aluno.setCpf_responsavel(obj.getCpf_responsavel());

        return alunoRepository.save(aluno);
    }

    public void delete(Long idAluno) {
        Aluno aluno = findById(idAluno);
        if (aluno != null) {
            try {
                this.alunoRepository.delete(aluno);
            } catch (Exception e) {
                throw new RuntimeException("Não foi possível excluir pois há entidades relacionadas!");
            }
        } else {
            throw new RuntimeException("Aluno não encontrado para exclusão!");
        }
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Boolean validation(Aluno obj) {
        Aluno aluno = findById(obj.getIdAluno());
        if (aluno != null) {
            String senha = aluno.getSenha_aluno();
            Boolean valid = passwordEncoder.matches(aluno.getSenha_aluno(), senha);
            return valid;
        } else {
            throw new RuntimeException("Aluno não encontrado para validação!");
        }
    }
}
