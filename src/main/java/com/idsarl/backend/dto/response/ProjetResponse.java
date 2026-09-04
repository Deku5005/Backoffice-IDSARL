package com.idsarl.backend.dto.response;

import com.idsarl.backend.Enum.StatutProjet;
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
public class ProjetResponse {

    private Long id;
    private String nom;
    private StatutProjet statut;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private BigDecimal montantApporte;
    private BigDecimal depenses;
    private BigDecimal marge;  // Calculé : montantApporte - depenses
}