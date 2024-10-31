package com.labo.laboratoire.Entities;
import java.time.LocalDate;  
import java.util.List;

import com.labo.laboratoire.Entities.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullLaboratoireResponse {
    private String nom;

    private String logo;

    private String nrc;

    private boolean active;

    private LocalDate dateActivation;

    private List<Utilisateur> utilisateurs;

}
