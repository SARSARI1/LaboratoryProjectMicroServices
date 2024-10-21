package com.medium.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medium.server.Entities.Laboratoire;
import com.medium.server.Repositories.LaboratoireRepository;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratoireService {

    @Autowired
    private LaboratoireRepository laboratoireRepository;

    public List<Laboratoire> getAllLaboratoires() {
        return laboratoireRepository.findAll();
    }

    public Optional<Laboratoire> getLaboratoireById(Long id) {
        return laboratoireRepository.findById(id);
    }

    public Laboratoire saveLaboratoire(Laboratoire laboratoire) {
        return laboratoireRepository.save(laboratoire);
    }

    public void deleteLaboratoire(Long id) {
        laboratoireRepository.deleteById(id);
    }
}
