package com.idsarl.backend.controllers;

import com.idsarl.backend.dto.request.ReseauSocialRequest;
import com.idsarl.backend.dto.response.ReseauSocialResponse;
import com.idsarl.backend.services.ReseauSocialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social-networks")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPPEUR')")
public class ReseauSocialController {

    private final ReseauSocialService reseauSocialService;

    @PostMapping
    public ResponseEntity<ReseauSocialResponse> createReseau(@Valid @RequestBody ReseauSocialRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reseauSocialService.createReseau(request));
    }

    @GetMapping
    public ResponseEntity<List<ReseauSocialResponse>> getAllReseaux() {
        return ResponseEntity.ok(reseauSocialService.getAllReseaux());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReseauSocialResponse> getReseauById(@PathVariable Long id) {
        return ResponseEntity.ok(reseauSocialService.getReseauById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReseauSocialResponse> updateReseau(
            @PathVariable Long id,
            @Valid @RequestBody ReseauSocialRequest request) {
        return ResponseEntity.ok(reseauSocialService.updateReseau(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteReseau(@PathVariable Long id) {
        reseauSocialService.deleteReseau(id);
        return ResponseEntity.noContent().build();
    }
}