package com.idsarl.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteBancaireResponse {

    private Long id;
    private String banque;
    private String numero;  // Déchiffré à la volée (masqué partiellement si besoin)
    private String devise;
    private BigDecimal solde;
}