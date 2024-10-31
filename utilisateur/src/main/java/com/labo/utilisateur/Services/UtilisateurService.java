package com.labo.utilisateur.Services;

import com.labo.utilisateur.Entities.Utilisateur;
import com.labo.utilisateur.Repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository repository;

    public List<Utilisateur> findAllUsers() {
        return repository.findAll();
    }

    public Utilisateur saveUser(Utilisateur utilisateur) {
        return repository.save(utilisateur); // Ensure save method returns the saved utilisateur
    }

    public List<Utilisateur> findAllUsersByLaboratoire(Long laboratoireId) {
        return repository.findAllByfkIdLaboratoire(laboratoireId);
    }

    public Utilisateur getUserByEmail(String email) {
        return repository.findById(email).orElse(null); // This will need to be added in UtilisateurRepository
    }

    public void deleteUser(String email) {
        repository.deleteById(email); // This will need to be added in UtilisateurRepository
    }
}
