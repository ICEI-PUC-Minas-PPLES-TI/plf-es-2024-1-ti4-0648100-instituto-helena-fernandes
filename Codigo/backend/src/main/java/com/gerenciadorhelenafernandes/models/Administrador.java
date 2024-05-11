package com.gerenciadorhelenafernandes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = Administrador.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Administrador {

   public static final String TABLE_NAME = "administrador";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_administrador", unique = true)
   @JsonProperty("id_administrador")
   private Long id_administrador;

   @Column(name = "emailAdmin", length = 100, nullable = false, unique = true)
   @NotBlank
   @JsonProperty("emailAdmin")
   private String emailAdmin;

   @Column(name = "senha_admin", length = 100, nullable = false)
   @NotBlank
   @JsonProperty("senha_admin")
   private String senha_admin;
}
