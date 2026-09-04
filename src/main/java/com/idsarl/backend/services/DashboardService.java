package com.idsarl.backend.services;

import com.idsarl.backend.dto.response.DashboardStatsResponse;
import com.idsarl.backend.dto.response.HebergementResponse;
import com.idsarl.backend.dto.response.ProjetResponse;
import com.idsarl.backend.Enum.StatutProjet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardService {

    private final ProjetService projetService;
    private final DeploiementService deploiementService;
    private final HebergementService hebergementService;
    private final CompteBancaireService compteBancaireService;

    public DashboardStatsResponse getDashboardStats() {
        long totalProjets = projetService.getAllProjets().size();
        BigDecimal totalCA = deploiementService.getAllDeploiements().stream()
                .map(d -> d.getMontant())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDepenses = projetService.getTotalDepenses();
        BigDecimal margeTotale = totalCA.subtract(totalDepenses);
        long alertesExpirations = hebergementService.getHebergementsExpirantBientot(30).size();
        long projetsEnRetard = projetService.countProjetsEnRetard();

        return DashboardStatsResponse.builder()
                .totalProjets(totalProjets)
                .totalCA(totalCA)
                .totalDepenses(totalDepenses)
                .margeTotale(margeTotale)
                .alertesExpirations(alertesExpirations)
                .projetsEnRetard(projetsEnRetard)
                .build();
    }

    public List<HebergementResponse> getAlertesExpirations() {
        return hebergementService.getHebergementsExpirantBientot(30);
    }

    public List<ProjetResponse> getAlertesProjetsRetard() {
        return projetService.getProjetsEnRetard();
    }
}