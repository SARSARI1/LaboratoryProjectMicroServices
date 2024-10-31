package com.labo.utilisateur.Services;

import com.labo.utilisateur.Entities.Utilisateur;
import com.labo.utilisateur.Repositories.UtilisateurRepository;
import com.labo.utilisateur.DTO.UtilisateurDTO; // Import the DTO class
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

    public Utilisateur saveUser(UtilisateurDTO utilisateurDTO) {
        // Convert DTO to entity
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setFkIdLaboratoire(utilisateurDTO.getFkIdLaboratoire());
        utilisateur.setImageurl(utilisateurDTO.getImageurl());
        utilisateur.setNomComplet(utilisateurDTO.getNomComplet());
        utilisateur.setProfession(utilisateurDTO.getProfession());
        utilisateur.setNumTel(utilisateurDTO.getNumTel());
        utilisateur.setSignature(utilisateurDTO.getSignature());
        utilisateur.setRole(utilisateurDTO.getRole());

        return repository.save(utilisateur);
    }

    public List<Utilisateur> findAllUsersByLaboratoire(Long laboratoireId) {
        return repository.findAllByfkIdLaboratoire(laboratoireId);
    }

    public Utilisateur getUserByEmail(String email) {
        return repository.findById(email).orElse(null);
    }

    public void deleteUser(String email) {
        repository.deleteById(email);
    }
}
