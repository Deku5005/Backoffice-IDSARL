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
public class DashboardStatsResponse {

    private long totalProjets;
    private BigDecimal totalCA;          // Chiffre d'affaires total
    private BigDecimal totalDepenses;
    private BigDecimal margeTotale;      // CA - Dépenses
    private long alertesExpirations;     // Hébergements expirant < 30 jours
    private long projetsEnRetard;
}