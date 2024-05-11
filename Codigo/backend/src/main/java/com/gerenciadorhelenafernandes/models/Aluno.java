package com.gerenciadorhelenafernandes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = Aluno.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aluno {

   public static final String TABLE_NAME = "aluno";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_aluno", unique = true)
   @JsonProperty("id_aluno")
   private Long id_aluno;

   @OneToMany(mappedBy = "aluno")
   private List<Notas> notas;

   @Column(name = "nome_aluno", length = 100, nullable = false)
   @NotBlank
   @JsonProperty("nome_aluno")
   private String nome_aluno;

   @Column(name = "emailAluno", length = 100, nullable = false)
   @NotBlank
   @JsonProperty("emailAluno")
   private String emailAluno;

   @Column(name = "senha_aluno", length = 100, nullable = false)
   @NotBlank
   @Size(min = 5, max = 200)
   private String senha_aluno;

   @Column(name = "cpf_aluno", length = 100, nullable = true)
   @Size(min = 2, max = 100)
   private String cpf_aluno;

   @Column(name = "data_nascimento", length = 100, nullable = true)
   private String data_nascimento;

   @Column(name = "serie", length = 50, nullable = true)
   private String serie;

   @Column(name = "alergia", nullable = true)
   private String alergia;

   @Column(name = "cidade", length = 100, nullable = true)
   private String cidade;

   @Column(name = "bairro", length = 100, nullable = true)
   private String bairro;

   @Column(name = "rua", length = 100, nullable = true)
   private String rua;

   @Column(name = "numero_casa", length = 100, nullable = true)
   private String numero_casa;

   @Column(name = "mais_informacoes", length = 100, nullable = true)
   private String mais_informacoes;

   @Column(name = "nome_responsavel", nullable = true)
   private String nome_responsavel;

   @Column(name = "cpf_responsavel", length = 100, nullable = true)
   private String cpf_responsavel;

   @Column(name = "telefone_responsavel", nullable = true)
   private String telefone_responsavel;

   @Column(name = "status_aluno", length = 1, nullable = true)
   private String status_aluno; //Null - Dados Faltando, 0 - Em análise, 1 - Aprovado, 2 - Reprovado


}
