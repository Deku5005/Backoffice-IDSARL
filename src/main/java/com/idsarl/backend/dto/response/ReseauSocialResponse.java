package com.idsarl.backend.dto.response;

import com.idsarl.backend.Enum.StatutReseauSocial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReseauSocialResponse {

    private Long id;
    private String plateforme;
    private String identifiant;
    private String responsable;
    private String motDePasse;  // Déchiffré à la volée
    private StatutReseauSocial statut;
}