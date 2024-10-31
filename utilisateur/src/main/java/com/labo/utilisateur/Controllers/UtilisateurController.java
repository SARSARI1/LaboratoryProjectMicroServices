package com.labo.utilisateur.Controllers;

import java.util.List;

import com.labo.utilisateur.Entities.Utilisateur;
import com.labo.utilisateur.Services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur save(@RequestBody Utilisateur utilisateur) {
        return service.saveUser(utilisateur); // Return the saved utilisateur
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> findAllUsers() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/laboratoires/{laboratoire-id}")
    public ResponseEntity<List<Utilisateur>> findAllUsersByLaboratoire(@PathVariable("laboratoire-id") Long laboratoireId) {
        return ResponseEntity.ok(service.findAllUsersByLaboratoire(laboratoireId));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Utilisateur> getUserByEmail(@PathVariable String email) {
        Utilisateur utilisateur = service.getUserByEmail(email);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utilisateur);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        service.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
