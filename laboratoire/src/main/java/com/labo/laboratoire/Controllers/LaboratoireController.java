package com.labo.laboratoire.Controllers;

import java.util.List;
import java.util.Optional;

import com.labo.laboratoire.Entities.Laboratoire;
import com.labo.laboratoire.Entities.FullLaboratoireResponse;
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
    public void save(@RequestBody Laboratoire laboratoire) {
        service.saveLaboratoire(laboratoire);
    }

    @GetMapping
    public ResponseEntity<List<Laboratoire>> findAllLaboratoires() {
        return ResponseEntity.ok(service.findAllLaboratoires());
    }

    @GetMapping("/with-utilisateurs/{laboratoire-id}")
    public ResponseEntity<FullLaboratoireResponse> findAllLaboratoires(@PathVariable("laboratoire-id") Long laboratoireId) {
        return ResponseEntity.ok(service.findLaboratoiresWithUtilisateurs(laboratoireId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratoire> getLaboratoireById(@PathVariable Long id) {
        Optional<Laboratoire> laboratoire = service.getLaboratoireById(id);
        return laboratoire.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratoire> updateLaboratoire(@PathVariable Long id, @RequestBody Laboratoire laboratoireDetails) {
        Optional<Laboratoire> optionalLaboratoire = service.getLaboratoireById(id);
        if (optionalLaboratoire.isPresent()) {
            Laboratoire laboratoire = optionalLaboratoire.get();
            laboratoire.setNom(laboratoireDetails.getNom());
            laboratoire.setLogo(laboratoireDetails.getLogo());
            laboratoire.setNrc(laboratoireDetails.getNrc());
            laboratoire.setActive(laboratoireDetails.isActive());
            laboratoire.setDateActivation(laboratoireDetails.getDateActivation());
            Laboratoire updatedLaboratoire = service.saveLaboratoire(laboratoire);
            return ResponseEntity.ok(updatedLaboratoire);
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
