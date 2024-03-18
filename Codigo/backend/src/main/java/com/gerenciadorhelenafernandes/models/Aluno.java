package com.gerenciadorhelenafernandes.models;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Aluno.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aluno {
    
    public static final String TABLE_NAME = "aluno";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id; 

   @Column(name = "nome", length = 100, nullable = false)

    @Size(min = 2, max = 100)
   private String nome;

   @Column(name = "cpf", length = 100, nullable = false)
   @Size(min = 2, max = 100)
      private String cpf;

   @Column(name = "nascimento", length = 100, nullable = false)
   @Size(min = 2, max = 100)
      private LocalDate nascimento;

    @Column(name = "endereco", length = 100, nullable = false)
      @Size(min = 2, max = 100)
    private String endereco;

    @Column(name = "alergia")
    private String alergia;

    @Column(name = "emailParente", nullable = false)
    @Email
    private String emailParente;

    @Column(name = "nomeParente", nullable = false)
    private String nomeParente;

    @Column(name = "telefoneParente", nullable = false)
    private String telefoneParente;

    @Column(name = "grauParentesco", nullable = false)
    private String grauParentesco;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "aceito", nullable = false)
    private boolean aceito;
}



