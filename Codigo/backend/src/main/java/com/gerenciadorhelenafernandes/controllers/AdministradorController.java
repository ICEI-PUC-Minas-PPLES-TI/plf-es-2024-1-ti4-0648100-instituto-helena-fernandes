package com.gerenciadorhelenafernandes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gerenciadorhelenafernandes.models.Administrador;
import com.gerenciadorhelenafernandes.services.AdministradorService;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    
    @GetMapping("/{id_administrador}")
    public ResponseEntity<?> findById(@PathVariable("id_administrador") Long id_administrador) {
        return ResponseEntity.status(200).body(administradorService.findById(id_administrador));
    }

    @PostMapping
    public ResponseEntity<Administrador> create(@RequestBody Administrador administrador) {
        administrador = administradorService.create(administrador);
        return ResponseEntity.status(201).body(administrador);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> validation(@RequestBody Administrador admin) {
        try {
            Long idAdmin = administradorService.validateLogin(admin.getEmailAdmin(), admin.getSenha_admin());
            return ResponseEntity.ok(idAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1L); // Indica erro
        }
    }
}
