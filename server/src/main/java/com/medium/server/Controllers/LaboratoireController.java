package com.medium.server.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medium.server.Entities.Laboratoire;
import com.medium.server.Services.LaboratoireService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/laboratoires")
public class LaboratoireController {

    @Autowired
    private LaboratoireService laboratoireService;

    @GetMapping
    public List<Laboratoire> getAllLaboratoires() {
        return laboratoireService.getAllLaboratoires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratoire> getLaboratoireById(@PathVariable Long id) {
        Optional<Laboratoire> laboratoire = laboratoireService.getLaboratoireById(id);
        if (laboratoire.isPresent()) {
            return ResponseEntity.ok(laboratoire.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Laboratoire createLaboratoire(@RequestBody Laboratoire laboratoire) {
        return laboratoireService.saveLaboratoire(laboratoire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratoire> updateLaboratoire(@PathVariable Long id, @RequestBody Laboratoire laboratoireDetails) {
        Optional<Laboratoire> optionalLaboratoire = laboratoireService.getLaboratoireById(id);
        if (optionalLaboratoire.isPresent()) {
            Laboratoire laboratoire = optionalLaboratoire.get();
            laboratoire.setNom(laboratoireDetails.getNom());
            laboratoire.setLogo(laboratoireDetails.getLogo());
            laboratoire.setNrc(laboratoireDetails.getNrc());
            laboratoire.setActive(laboratoireDetails.isActive());
            laboratoire.setDateActivation(laboratoireDetails.getDateActivation());
            Laboratoire updatedLaboratoire = laboratoireService.saveLaboratoire(laboratoire);
            return ResponseEntity.ok(updatedLaboratoire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoire(@PathVariable Long id) {
        laboratoireService.deleteLaboratoire(id);
        return ResponseEntity.noContent().build();
    }
}

