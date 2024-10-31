package com.labo.laboratoire.Services;

import java.util.List;
import java.util.Optional;

import com.labo.laboratoire.Entities.Laboratoire;
import com.labo.laboratoire.Entities.FullLaboratoireResponse;
import com.labo.laboratoire.Repositories.LaboratoireRepository;
import com.labo.laboratoire.UtilisateurClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoireService {

    @Autowired
    private final LaboratoireRepository repository;
    private final UtilisateurClient client;

    // Constructor injection
    public LaboratoireService(LaboratoireRepository repository, UtilisateurClient client) {
        this.repository = repository;
        this.client = client;
    }

    public List<Laboratoire> findAllLaboratoires() {
        return repository.findAll();
    }

    public Optional<Laboratoire> getLaboratoireById(Long id) {
        return repository.findById(id);
    }

    public Laboratoire saveLaboratoire(Laboratoire laboratoire) {
        return repository.save(laboratoire);
    }

    public void deleteLaboratoire(Long id) {
        repository.deleteById(id);
    }

    public FullLaboratoireResponse findLaboratoiresWithUtilisateurs(Long laboratoireId) {
        var laboratoire = repository.findById(laboratoireId)
                .orElse(Laboratoire.builder()
                        .nom("Not Found")
                        .logo("not found")
                        .nrc("not found")
                        .build());

        var utilisateurs = client.findAllUsersByLaboratoire(laboratoireId); // Find all users from the user service

        return FullLaboratoireResponse.builder()
                .nom(laboratoire.getNom())
                .logo(laboratoire.getLogo())
                .nrc(laboratoire.getNrc())
                .utilisateurs(utilisateurs) // Set utilisateurs here
                .build();
    }
}
