package com.labo.utilisateur.Repositories;

import com.labo.utilisateur.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> { // Changed Long to String for email

    List<Utilisateur> findAllByfkIdLaboratoire(Long laboratoireId);

    // Additional query methods can be defined here if needed
}
