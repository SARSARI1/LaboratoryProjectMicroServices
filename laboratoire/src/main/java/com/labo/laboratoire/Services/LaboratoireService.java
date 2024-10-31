package com.labo.laboratoire.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.labo.laboratoire.Entities.Utilisateur; // Update the package name accordingly

import com.labo.laboratoire.Entities.Laboratoire;
import com.labo.laboratoire.Entities.FullLaboratoireResponse;
import com.labo.laboratoire.Repositories.LaboratoireRepository;
import com.labo.laboratoire.Client.UtilisateurClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labo.laboratoire.DTOs.FullLaboratoireResponseDTO;
import com.labo.laboratoire.DTOs.LaboratoireDTO;
import com.labo.laboratoire.DTOs.UtilisateurDTO;

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

    public List<LaboratoireDTO> findAllLaboratoires() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<LaboratoireDTO> getLaboratoireById(Long id) {
        return repository.findById(id).map(this::mapToDTO);
    }

    public LaboratoireDTO saveLaboratoire(LaboratoireDTO laboratoireDTO) {
        Laboratoire laboratoire = mapToEntity(laboratoireDTO);
        return mapToDTO(repository.save(laboratoire));
    }

    public void deleteLaboratoire(Long id) {
        repository.deleteById(id);
    }

    public FullLaboratoireResponseDTO findLaboratoiresWithUtilisateurs(Long laboratoireId) {
        Laboratoire laboratoire = repository.findById(laboratoireId)
                .orElseThrow(() -> new RuntimeException("Laboratoire not found"));

        List<UtilisateurDTO> utilisateurs = client.findAllUsersByLaboratoire(laboratoireId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return FullLaboratoireResponseDTO.builder()
                .laboratoire(mapToDTO(laboratoire))
                .utilisateurs(utilisateurs)
                .build();
    }

    private LaboratoireDTO mapToDTO(Laboratoire laboratoire) {
        return LaboratoireDTO.builder()
                .id(laboratoire.getId())
                .nom(laboratoire.getNom())
                .logo(laboratoire.getLogo())
                .nrc(laboratoire.getNrc())
                .active(laboratoire.isActive())
                .dateActivation(laboratoire.getDateActivation())
                .build();
    }

    private Laboratoire mapToEntity(LaboratoireDTO laboratoireDTO) {
        return Laboratoire.builder()
                .id(laboratoireDTO.getId())
                .nom(laboratoireDTO.getNom())
                .logo(laboratoireDTO.getLogo())
                .nrc(laboratoireDTO.getNrc())
                .active(laboratoireDTO.isActive())
                .dateActivation(laboratoireDTO.getDateActivation())
                .build();
    }

    private UtilisateurDTO mapToDTO(Utilisateur utilisateur) {
        return UtilisateurDTO.builder()
                .email(utilisateur.getEmail())
                .fkIdLaboratoire(utilisateur.getFkIdLaboratoire())
                .imageurl(utilisateur.getImageurl())
                .nomComplet(utilisateur.getNomComplet())
                .profession(utilisateur.getProfession())
                .numTel(utilisateur.getNumTel())
                .signature(utilisateur.getSignature())
                .role(utilisateur.getRole())
                .build();
    }
}
