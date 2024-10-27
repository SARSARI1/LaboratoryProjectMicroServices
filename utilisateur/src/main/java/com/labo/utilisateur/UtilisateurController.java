package com.labo.utilisateur;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@RequiredArgsConstructor

public class UtilisateurController {
  private final UtilisateurService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Utilisateur utilisateur) {
       service.saveUser(utilisateur);
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> findAllUsers(){
        return ResponseEntity.ok(service.findAllUsers());
    }
      

    @GetMapping("/laboratoires/{laboratoire-id}")
    public ResponseEntity<List<Utilisateur>> findAllUsersByLaboratoire(@PathVariable("laboratoire-id") Long laboratoireId ){
        return ResponseEntity.ok(service.findAllUsersByLaboratoire(laboratoireId));
    }

}
