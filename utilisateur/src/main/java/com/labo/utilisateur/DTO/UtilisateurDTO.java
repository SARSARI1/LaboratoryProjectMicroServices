package com.labo.utilisateur.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDTO {
    private String email;
    private Long fkIdLaboratoire;
    private String imageurl;
    private String nomComplet;
    private String profession;
    private String numTel;
    private String signature;
    private String role;
}
