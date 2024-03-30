package com.gerenciadorhelenafernandes.services;

import java.util.List;
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

    @SuppressWarnings("null")
    public Aluno findById(Long id) {
        Optional<Aluno> aluno = this.alunoRepository.findById(id);
        return aluno.orElseThrow(() -> new RuntimeException(
                "Aluno não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    // Salvar no Banco de Dados
    @Transactional
    public Aluno create(Aluno obj) {
        obj.setId_aluno(null);
        return this.alunoRepository.save(obj);
    }

    @Transactional
    public Aluno update(Aluno obj, Long id) {
        Aluno aluno = findById(id);
        aluno.setCpf_aluno(obj.getCpf_aluno());
        aluno.setData_nascimento(obj.getData_nascimento());
        aluno.setAlergia(obj.getAlergia());
        aluno.setCidade(obj.getCidade());
        aluno.setBairro(obj.getBairro());
        aluno.setRua(obj.getRua());
        aluno.setNumero_casa(obj.getNumero_casa());
        aluno.setMais_informacoes(obj.getMais_informacoes());
        aluno.setNome_responsavel(obj.getNome_responsavel());
        aluno.setCpf_responsavel(obj.getCpf_responsavel());
        aluno.setTelefone_responsavel(obj.getTelefone_responsavel());

        return alunoRepository.save(aluno);
    }

    public void delete(Long id_aluno) {
        Aluno aluno = findById(id_aluno);
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

    public Boolean validateLogin(String email, String senha) {
        Optional<Aluno> alunoOptional = alunoRepository.findByEmailAluno(email);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            return senha.equals(aluno.getSenha_aluno());
        } else {
            throw new RuntimeException("Aluno não encontrado com o email fornecido.");
        }
    }

    public Long findIdByEmail(String email) {
        Optional<Aluno> alunoOptional = alunoRepository.findByEmailAluno(email);
        if (alunoOptional.isPresent()) {
            return alunoOptional.get().getId_aluno();
        } else {
            throw new RuntimeException("Aluno não encontrado com o email fornecido.");
        }
    }
}
