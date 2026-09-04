package com.idsarl.backend.dto.request;

import com.idsarl.backend.Enum.Periodicite;
import com.idsarl.backend.Enum.StatutDeploiement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class DeploiementRequest {

    @NotNull(message = "L'ID du produit est obligatoire")
    private Long produitId;

    @NotNull(message = "L'ID du client est obligatoire")
    private Long clientId;

    @NotNull(message = "La date de déploiement est obligatoire")
    private LocalDate dateDeploiement;

    @NotNull(message = "Le statut est obligatoire")
    private StatutDeploiement statut;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;

    @NotNull(message = "La périodicité est obligatoire")
    private Periodicite periodicite;
}