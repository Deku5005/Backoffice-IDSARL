package com.idsarl.backend.controllers;

import com.idsarl.backend.dto.request.PageContenuRequest;
import com.idsarl.backend.dto.response.PageContenuResponse;
import com.idsarl.backend.Enum.TypePageContenu;
import com.idsarl.backend.services.PageContenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPPEUR')")
public class PageContenuController {

    private final PageContenuService pageContenuService;

    @PostMapping
    public ResponseEntity<PageContenuResponse> createPage(@Valid @RequestBody PageContenuRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pageContenuService.createPage(request));
    }

    @GetMapping
    public ResponseEntity<List<PageContenuResponse>> getAllPages() {
        return ResponseEntity.ok(pageContenuService.getAllPages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageContenuResponse> getPageById(@PathVariable Long id) {
        return ResponseEntity.ok(pageContenuService.getPageById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<PageContenuResponse> getPageByType(@PathVariable TypePageContenu type) {
        return ResponseEntity.ok(pageContenuService.getPageByType(type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PageContenuResponse> updatePage(
            @PathVariable Long id,
            @Valid @RequestBody PageContenuRequest request) {
        return ResponseEntity.ok(pageContenuService.updatePage(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        pageContenuService.deletePage(id);
        return ResponseEntity.noContent().build();
    }
}