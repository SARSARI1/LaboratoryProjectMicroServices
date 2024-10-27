package com.labo.laboratoire;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RestController
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
    public ResponseEntity<List<Laboratoire>> findAllLaboratoires(){
        return ResponseEntity.ok(service.findAllLaboratoires());
    }

    @GetMapping("/with-utilisateurs/{laboratoire-id}")
    public ResponseEntity<FullLaboratoireResponse> findAllLaboratoires( @PathVariable("laboratoire-id") Long laboratoireId  ){
        return ResponseEntity.ok(service.findLaboratoiresWithUtilisateurs(laboratoireId));
    }
      
}
