package com.labo.laboratoire.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullLaboratoireResponseDTO {
    private LaboratoireDTO laboratoire;
    private List<UtilisateurDTO> utilisateurs;
}
