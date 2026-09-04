package com.idsarl.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HebergementResponse {

    private Long id;
    private String plateforme;
    private String domaine;
    private LocalDate dateExpiration;
    private BigDecimal cout;
    private Long produitId;
    private String produitNom;  // Pour afficher le nom du produit associé
}