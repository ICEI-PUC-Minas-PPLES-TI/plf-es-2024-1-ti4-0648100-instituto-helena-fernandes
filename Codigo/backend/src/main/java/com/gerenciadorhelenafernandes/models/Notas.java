package com.gerenciadorhelenafernandes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "id_notas")
    private Long idNotas;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_disciplina") // Chave estrangeira que faz referência à tabela disciplina
    private Disciplina disciplina;


    @Column(name = "prova1")
    private Double prova1;

    @Column(name = "prova2")
    private Double prova2;
//aaaa
    @Column(name = "prova3")
    private Double prova3;

    @Column(name = "trabalho1")
    private Double trabalho1;

    @Column(name = "trabalho2")
    private Double trabalho2;

    @Column(name = "trabalho3")
    private Double trabalho3;

}