package com.gerenciadorhelenafernandes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Professor.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Professor {

    public static final String TABLE_NAME = "professor";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professor", unique = true)
    private Long id_Professor;

    @Column(name = "nome_professor", length = 100, nullable = false)
    @NotBlank
    private String nome_professor;

    @Column(name = "cpf_professor", length = 100, nullable = true)
    @Size(min = 2, max = 100)
    private String cpf_professor;

    @Column(name = "data_nascimento", length = 100, nullable = true)
    private String data_nascimento;

    @Column(name = "formacao_professor", length = 100, nullable = true)
    private String formacao_professor;

    @Column(name = "disciplina_professor", length = 100, nullable = true)
    private String disciplina_professor;

    @Column(name = "email_professor", length = 100, nullable = false)
    @NotBlank
    private String email_professor;

}
