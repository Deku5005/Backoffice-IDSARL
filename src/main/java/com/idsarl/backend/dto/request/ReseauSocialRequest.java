package com.idsarl.backend.dto.request;

import com.idsarl.backend.Enum.StatutReseauSocial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReseauSocialRequest {

    @NotBlank(message = "La plateforme est obligatoire")
    private String plateforme;

    @NotBlank(message = "L'identifiant est obligatoire")
    private String identifiant;

    @NotBlank(message = "Le responsable est obligatoire")
    private String responsable;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String motDePasse;

    @NotNull(message = "Le statut est obligatoire")
    private StatutReseauSocial statut;
}