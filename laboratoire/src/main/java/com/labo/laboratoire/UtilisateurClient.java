package com.labo.laboratoire;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name="utilisateur-service", url="${application.config.utilisateurs-url}")
public interface UtilisateurClient {

    @GetMapping("/laboratoires/{laboratoire-id}")
    List<Utilisateur> findAllUsersByLaboratoire(@PathVariable("laboratoire-id") Long laboratoireId);
}

