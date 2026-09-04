package com.idsarl.backend.dto.response;

import com.idsarl.backend.Enum.StatutProduit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitResponse {

    private Long id;
    private String nom;
    private String description;
    private String technologie;
    private StatutProduit statut;
    private String historiqueVersions;
}