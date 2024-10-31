package com.labo.laboratoire.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaboratoireDTO {
    private Long id;
    private String nom;
    private String logo;
    private String nrc;
    private boolean active;
    private LocalDate dateActivation;
}