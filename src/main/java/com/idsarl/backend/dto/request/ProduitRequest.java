package com.idsarl.backend.dto.request;

import com.idsarl.backend.Enum.StatutProduit;
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
public class ProduitRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    private String description;

    @NotBlank(message = "La technologie est obligatoire")
    private String technologie;

    @NotNull(message = "Le statut est obligatoire")
    private StatutProduit statut;

    private String historiqueVersions;
}