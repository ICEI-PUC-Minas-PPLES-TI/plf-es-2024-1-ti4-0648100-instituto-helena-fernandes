package com.gerenciadorhelenafernandes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    @JsonBackReference // Indica o lado "filho" da relação, onde a serialização deve parar para evitar
                       // loops
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    @JsonBackReference
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    @JsonBackReference
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @Column(name = "prova1")
    private Double prova1;

    @Column(name = "prova2")
    private Double prova2;
    // aaaa
    @Column(name = "prova3")
    private Double prova3;

    @Column(name = "trabalho1")
    private Double trabalho1;

    @Column(name = "trabalho2")
    private Double trabalho2;

    @Column(name = "trabalho3")
    private Double trabalho3;

}
