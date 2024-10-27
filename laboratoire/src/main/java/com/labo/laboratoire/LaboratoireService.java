package com.labo.laboratoire;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaboratoireService {
    private final LaboratoireRepository repository;
    private final UtilisateurClient client;

    public List<Laboratoire> findAllLaboratoires() {
        return repository.findAll();
    }

    public void saveLaboratoire(Laboratoire laboratoire) {
        repository.save(laboratoire);
    }

    public FullLaboratoireResponse findLaboratoiresWithUtilisateurs(Long laboratoireId) {
        var laboratoire = repository.findById(laboratoireId)
                .orElse(Laboratoire.builder()
                        .nom("Not Found")
                        .logo("not found")
                        .nrc("not found")
                        .build());
        
        var utilisateurs = client.findAllUsersByLaboratoire(laboratoireId); // find all users from the user service
        
        return FullLaboratoireResponse.builder()
                .nom(laboratoire.getNom())
                .logo(laboratoire.getLogo())
                .nrc(laboratoire.getNrc())
                .utilisateurs(utilisateurs) // Set utilisateurs here
                .build();
    }
}
