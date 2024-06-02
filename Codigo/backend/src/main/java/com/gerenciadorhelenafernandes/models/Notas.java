package com.gerenciadorhelenafernandes.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Notas.TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notas {
    public static final String TABLE_NAME = "notas";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notas", unique = true)
    private Long idNotas;

    @ManyToMany
    @JoinTable(
        name = "nota_aluno",
        joinColumns = @JoinColumn(name = "id_nota"),
        inverseJoinColumns = @JoinColumn(name = "id_aluno")
    )
    private List<Aluno> alunos;

    @ManyToMany
    @JoinTable(
        name = "nota_professor",
        joinColumns = @JoinColumn(name = "id_nota"),
        inverseJoinColumns = @JoinColumn(name = "id_professor")
    )
    private List<Professor> professores;

    @ManyToMany
    @JoinTable(
        name = "nota_disciplina",
        joinColumns = @JoinColumn(name = "id_nota"),
        inverseJoinColumns = @JoinColumn(name = "id_disciplina")
    )
    private List<Disciplina> disciplinas;

    @ManyToMany
    @JoinTable(
        name = "nota_turma",
        joinColumns = @JoinColumn(name = "id_nota"),
        inverseJoinColumns = @JoinColumn(name = "id_turma")
    )
    private List<Turma> turmas;

    @Column(name = "prova1")
    private Double prova1;

    @Column(name = "prova2")
    private Double prova2;

    @Column(name = "prova3")
    private Double prova3;

    @Column(name = "trabalho1")
    private Double trabalho1;

    @Column(name = "trabalho2")
    private Double trabalho2;

    @Column(name = "trabalho3")
    private Double trabalho3;
}
