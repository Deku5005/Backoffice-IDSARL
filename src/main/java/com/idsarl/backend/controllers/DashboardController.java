package com.idsarl.backend.controllers;

import com.idsarl.backend.dto.response.DashboardStatsResponse;
import com.idsarl.backend.dto.response.HebergementResponse;
import com.idsarl.backend.dto.response.ProjetResponse;
import com.idsarl.backend.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'FINANCE', 'COMMERCIAL', 'DEVELOPPEUR', 'CONSULTATION')")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponse> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<HebergementResponse>> getAlertesExpirations() {
        return ResponseEntity.ok(dashboardService.getAlertesExpirations());
    }

    @GetMapping("/alerts/projets-retard")
    public ResponseEntity<List<ProjetResponse>> getAlertesProjetsRetard() {
        return ResponseEntity.ok(dashboardService.getAlertesProjetsRetard());
    }
}