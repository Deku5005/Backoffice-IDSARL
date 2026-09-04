package com.idsarl.backend.controllers;

import com.idsarl.backend.dto.request.CompteBancaireRequest;
import com.idsarl.backend.dto.response.CompteBancaireResponse;
import com.idsarl.backend.services.CompteBancaireService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'FINANCE')")
public class CompteBancaireController {

    private final CompteBancaireService compteBancaireService;

    @PostMapping
    public ResponseEntity<CompteBancaireResponse> createCompte(@Valid @RequestBody CompteBancaireRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(compteBancaireService.createCompte(request));
    }

    @GetMapping
    public ResponseEntity<List<CompteBancaireResponse>> getAllComptes() {
        return ResponseEntity.ok(compteBancaireService.getAllComptes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteBancaireResponse> getCompteById(@PathVariable Long id) {
        return ResponseEntity.ok(compteBancaireService.getCompteById(id));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<CompteBancaireResponse> getCompteByNumero(@PathVariable String numero) {
        return ResponseEntity.ok(compteBancaireService.getCompteByNumero(numero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompteBancaireResponse> updateCompte(
            @PathVariable Long id,
            @Valid @RequestBody CompteBancaireRequest request) {
        return ResponseEntity.ok(compteBancaireService.updateCompte(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        compteBancaireService.deleteCompte(id);
        return ResponseEntity.noContent().build();
    }
}