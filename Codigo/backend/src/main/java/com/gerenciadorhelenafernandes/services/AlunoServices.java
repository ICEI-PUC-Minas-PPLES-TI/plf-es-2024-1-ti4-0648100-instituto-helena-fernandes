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

    public List<Aluno> findByStatus(String status) {
        return alunoRepository.findByStatusAluno(status);
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
        aluno.setSerie(obj.getSerie());
        aluno.setAlergia(obj.getAlergia());
        aluno.setCidade(obj.getCidade());
        aluno.setBairro(obj.getBairro());
        aluno.setRua(obj.getRua());
        aluno.setNumero_casa(obj.getNumero_casa());
        aluno.setMais_informacoes(obj.getMais_informacoes());
        aluno.setNome_responsavel(obj.getNome_responsavel());
        aluno.setCpf_responsavel(obj.getCpf_responsavel());
        aluno.setTelefone_responsavel(obj.getTelefone_responsavel());
        aluno.setStatus_aluno(obj.getStatus_aluno());

        return alunoRepository.save(aluno);
    }

    public Aluno updateStatus(Long id_aluno, String novoStatus) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id_aluno);
        if (alunoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            aluno.setStatus_aluno(novoStatus);
            return alunoRepository.save(aluno);
        } else {
            throw new RuntimeException("Aluno não encontrado");
        }
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

    public Long validateLogin(String email, String senha) {
        Optional<Aluno> alunoOptional = alunoRepository.findByEmailAluno(email);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            if(senha.equals(aluno.getSenha_aluno())) {
                // Login bem-sucedido
                if ("1".equals(aluno.getStatus_aluno())) {
                    return -aluno.getId_aluno(); // ID negativo para status 1
                } else if ("2".equals(aluno.getStatus_aluno())) {
                    return aluno.getId_aluno(); // ID positivo para status 2, mas com lógica especial no frontend
                }
                return aluno.getId_aluno(); // ID positivo normal para outros casos
            }
        }
        throw new RuntimeException("Aluno não encontrado ou senha inválida.");
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
