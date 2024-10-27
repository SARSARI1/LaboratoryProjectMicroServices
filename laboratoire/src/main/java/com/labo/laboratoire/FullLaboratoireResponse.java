package com.labo.laboratoire;
import java.time.LocalDate;  
import java.util.List;
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
