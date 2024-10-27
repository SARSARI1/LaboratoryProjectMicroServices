package com.labo.utilisateur;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
List<Utilisateur> findAllByfkIdLaboratoire(Long laboratoireId);
    
} 