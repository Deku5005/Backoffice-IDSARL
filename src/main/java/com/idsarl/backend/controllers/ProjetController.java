package com.idsarl.backend.controllers;

import com.idsarl.backend.dto.request.ProjetRequest;
import com.idsarl.backend.dto.response.ProjetResponse;
import com.idsarl.backend.Enum.StatutProjet;
import com.idsarl.backend.services.ProjetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'FINANCE', 'COMMERCIAL')")
public class ProjetController {

    private final ProjetService projetService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE')")
    public ResponseEntity<ProjetResponse> createProjet(@Valid @RequestBody ProjetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projetService.createProjet(request));
    }

    @GetMapping
    public ResponseEntity<List<ProjetResponse>> getAllProjets() {
        return ResponseEntity.ok(projetService.getAllProjets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetResponse> getProjetById(@PathVariable Long id) {
        return ResponseEntity.ok(projetService.getProjetById(id));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<ProjetResponse>> getProjetsByStatut(@PathVariable StatutProjet statut) {
        return ResponseEntity.ok(projetService.getProjetsByStatut(statut));
    }

    @GetMapping("/retard")
    public ResponseEntity<List<ProjetResponse>> getProjetsEnRetard() {
        return ResponseEntity.ok(projetService.getProjetsEnRetard());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE')")
    public ResponseEntity<ProjetResponse> updateProjet(
            @PathVariable Long id,
            @Valid @RequestBody ProjetRequest request) {
        return ResponseEntity.ok(projetService.updateProjet(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }
}