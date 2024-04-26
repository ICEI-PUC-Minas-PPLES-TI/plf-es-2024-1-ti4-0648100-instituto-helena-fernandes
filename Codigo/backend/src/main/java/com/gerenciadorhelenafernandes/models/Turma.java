package com.gerenciadorhelenafernandes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Turma.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Turma {

    public static final String TABLE_NAME = "turma";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma", unique = true)
    private Long id_turma;

    @Column(name = "nome_turma")
    private String nome_turma;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
}