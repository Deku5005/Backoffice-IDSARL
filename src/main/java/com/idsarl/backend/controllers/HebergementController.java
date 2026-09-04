package com.idsarl.backend.controllers;

import com.idsarl.backend.dto.request.HebergementRequest;
import com.idsarl.backend.dto.response.HebergementResponse;
import com.idsarl.backend.services.HebergementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostings")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPPEUR')")
public class HebergementController {

    private final HebergementService hebergementService;

    @PostMapping
    public ResponseEntity<HebergementResponse> createHebergement(@Valid @RequestBody HebergementRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hebergementService.createHebergement(request));
    }

    @GetMapping
    public ResponseEntity<List<HebergementResponse>> getAllHebergements() {
        return ResponseEntity.ok(hebergementService.getAllHebergements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HebergementResponse> getHebergementById(@PathVariable Long id) {
        return ResponseEntity.ok(hebergementService.getHebergementById(id));
    }

    @GetMapping("/expiring/{jours}")
    public ResponseEntity<List<HebergementResponse>> getHebergementsExpirantBientot(@PathVariable int jours) {
        return ResponseEntity.ok(hebergementService.getHebergementsExpirantBientot(jours));
    }

    @GetMapping("/produit/{produitId}")
    public ResponseEntity<List<HebergementResponse>> getHebergementsByProduit(@PathVariable Long produitId) {
        return ResponseEntity.ok(hebergementService.getHebergementsByProduit(produitId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HebergementResponse> updateHebergement(
            @PathVariable Long id,
            @Valid @RequestBody HebergementRequest request) {
        return ResponseEntity.ok(hebergementService.updateHebergement(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteHebergement(@PathVariable Long id) {
        hebergementService.deleteHebergement(id);
        return ResponseEntity.noContent().build();
    }
}