package com.idsarl.backend.controllers;

import com.idsarl.backend.dto.request.MouvementBancaireRequest;
import com.idsarl.backend.dto.response.MouvementBancaireResponse;
import com.idsarl.backend.services.MouvementBancaireService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bank-movements")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'FINANCE')")
public class MouvementBancaireController {

    private final MouvementBancaireService mouvementBancaireService;

    @PostMapping
    public ResponseEntity<MouvementBancaireResponse> createMouvement(@Valid @RequestBody MouvementBancaireRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mouvementBancaireService.createMouvement(request));
    }

    @GetMapping("/compte/{compteId}")
    public ResponseEntity<List<MouvementBancaireResponse>> getMouvementsByCompte(@PathVariable Long compteId) {
        return ResponseEntity.ok(mouvementBancaireService.getMouvementsByCompte(compteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MouvementBancaireResponse> getMouvementById(@PathVariable Long id) {
        return ResponseEntity.ok(mouvementBancaireService.getMouvementById(id));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<MouvementBancaireResponse>> getMouvementsByDateRange(
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin) {
        return ResponseEntity.ok(mouvementBancaireService.getMouvementsByDateRange(debut, fin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MouvementBancaireResponse> updateMouvement(
            @PathVariable Long id,
            @Valid @RequestBody MouvementBancaireRequest request) {
        return ResponseEntity.ok(mouvementBancaireService.updateMouvement(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMouvement(@PathVariable Long id) {
        mouvementBancaireService.deleteMouvement(id);
        return ResponseEntity.noContent().build();
    }
}