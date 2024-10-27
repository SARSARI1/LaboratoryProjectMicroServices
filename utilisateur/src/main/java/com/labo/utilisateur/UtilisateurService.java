package com.labo.utilisateur;

import org.springframework.stereotype.Service;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor


public class UtilisateurService {
    
    private final UtilisateurRepository repository;

    public List<Utilisateur> findAllUsers() {
        return repository.findAll();
    }


    public void saveUser(Utilisateur utilisateur) {
        repository.save(utilisateur);
    }
    
    public List<Utilisateur> findAllUsersByLaboratoire(Long laboratoireId){
        return repository.findAllByfkIdLaboratoire(laboratoireId);
    }



}
