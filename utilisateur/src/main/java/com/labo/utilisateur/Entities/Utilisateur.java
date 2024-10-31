package com.labo.utilisateur.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "fk_id_laboratoire", nullable = false)
    private Long fkIdLaboratoire;

    @Column(name = "imageurl", nullable = true)
    private String imageurl;

    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;

    @Column(nullable = false)
    private String profession;

    @Column(name = "num_tel", nullable = false)
    private String numTel;

    private String signature;

    @Column(nullable = false)
    private String role;
}
