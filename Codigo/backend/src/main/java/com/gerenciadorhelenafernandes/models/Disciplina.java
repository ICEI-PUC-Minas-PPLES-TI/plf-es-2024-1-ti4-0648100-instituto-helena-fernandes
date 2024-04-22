package com.gerenciadorhelenafernandes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Disciplina.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Disciplina {

    public static final String TABLE_NAME = "disciplina";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disciplina", unique = true)
    private Long idDisciplina;

    @Column(name = "nome_disciplina", length = 100, nullable = false)
    @NotBlank(message = "O nome da disciplina é obrigatório.")
    private String nomeDisciplina;

    @Column(name = "carga_horaria", nullable = false)
    @NotBlank(message = "A carga horária é obrigatória.")
    private Integer cargaHoraria;

    // Outros atributos e métodos podem ser adicionados aqui conforme necessário.
}
