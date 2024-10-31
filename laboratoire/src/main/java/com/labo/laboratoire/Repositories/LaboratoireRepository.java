package com.labo.laboratoire.Repositories;

import com.labo.laboratoire.Entities.Laboratoire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoireRepository extends JpaRepository<Laboratoire, Long> {
    
}
