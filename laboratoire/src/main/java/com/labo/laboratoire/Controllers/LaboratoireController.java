package com.labo.laboratoire.Controllers;

import java.util.List;
import java.util.Optional;

import com.labo.laboratoire.DTOs.FullLaboratoireResponseDTO;
import com.labo.laboratoire.DTOs.LaboratoireDTO;
import com.labo.laboratoire.Services.LaboratoireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/laboratoires")
@RequiredArgsConstructor
public class LaboratoireController {
    private final LaboratoireService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody LaboratoireDTO laboratoireDTO) {
        service.saveLaboratoire(laboratoireDTO);
    }

    @GetMapping
    public ResponseEntity<List<LaboratoireDTO>> findAllLaboratoires() {
        return ResponseEntity.ok(service.findAllLaboratoires());
    }

    @GetMapping("/with-utilisateurs/{laboratoire-id}")
    public ResponseEntity<FullLaboratoireResponseDTO> findAllLaboratoires(@PathVariable("laboratoire-id") Long laboratoireId) {
        return ResponseEntity.ok(service.findLaboratoiresWithUtilisateurs(laboratoireId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoireDTO> getLaboratoireById(@PathVariable Long id) {
        Optional<LaboratoireDTO> laboratoireDTO = service.getLaboratoireById(id);
        return laboratoireDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratoireDTO> updateLaboratoire(@PathVariable Long id, @RequestBody LaboratoireDTO laboratoireDetails) {
        Optional<LaboratoireDTO> optionalLaboratoireDTO = service.getLaboratoireById(id);
        if (optionalLaboratoireDTO.isPresent()) {
            LaboratoireDTO laboratoireDTO = optionalLaboratoireDTO.get();
            laboratoireDTO.setNom(laboratoireDetails.getNom());
            laboratoireDTO.setLogo(laboratoireDetails.getLogo());
            laboratoireDTO.setNrc(laboratoireDetails.getNrc());
            laboratoireDTO.setActive(laboratoireDetails.isActive());
            laboratoireDTO.setDateActivation(laboratoireDetails.getDateActivation());
            LaboratoireDTO updatedLaboratoireDTO = service.saveLaboratoire(laboratoireDTO);
            return ResponseEntity.ok(updatedLaboratoireDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoire(@PathVariable Long id) {
        service.deleteLaboratoire(id);
        return ResponseEntity.noContent().build();
    }
}
