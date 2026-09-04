package com.idsarl.backend.dto.response;

import com.idsarl.backend.Enum.Periodicite;
import com.idsarl.backend.Enum.StatutDeploiement;
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
public class DeploiementResponse {

    private Long id;
    private Long produitId;
    private String produitNom;
    private Long clientId;
    private String clientNom;
    private LocalDate dateDeploiement;
    private StatutDeploiement statut;
    private BigDecimal montant;
    private Periodicite periodicite;
}