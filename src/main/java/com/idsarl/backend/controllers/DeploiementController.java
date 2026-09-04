package com.idsarl.backend.controllers;

import com.idsarl.backend.dto.request.DeploiementRequest;
import com.idsarl.backend.dto.response.DeploiementResponse;
import com.idsarl.backend.services.DeploiementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deployments")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'COMMERCIAL')")
public class DeploiementController {

    private final DeploiementService deploiementService;

    @PostMapping
    public ResponseEntity<DeploiementResponse> createDeploiement(@Valid @RequestBody DeploiementRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deploiementService.createDeploiement(request));
    }

    @GetMapping
    public ResponseEntity<List<DeploiementResponse>> getAllDeploiements() {
        return ResponseEntity.ok(deploiementService.getAllDeploiements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeploiementResponse> getDeploiementById(@PathVariable Long id) {
        return ResponseEntity.ok(deploiementService.getDeploiementById(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<DeploiementResponse>> getDeploiementsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(deploiementService.getDeploiementsByClient(clientId));
    }

    @GetMapping("/produit/{produitId}")
    public ResponseEntity<List<DeploiementResponse>> getDeploiementsByProduit(@PathVariable Long produitId) {
        return ResponseEntity.ok(deploiementService.getDeploiementsByProduit(produitId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeploiementResponse> updateDeploiement(
            @PathVariable Long id,
            @Valid @RequestBody DeploiementRequest request) {
        return ResponseEntity.ok(deploiementService.updateDeploiement(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDeploiement(@PathVariable Long id) {
        deploiementService.deleteDeploiement(id);
        return ResponseEntity.noContent().build();
    }
}