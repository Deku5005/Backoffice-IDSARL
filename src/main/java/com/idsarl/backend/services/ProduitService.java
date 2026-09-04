package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.ProduitRequest;
import com.idsarl.backend.dto.response.ProduitResponse;
import com.idsarl.backend.Entite.Produit;
import com.idsarl.backend.mapper.ProduitMapper;
import com.idsarl.backend.repositories.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;

    public ProduitResponse createProduit(ProduitRequest request) {
        if (produitRepository.existsByNom(request.getNom())) {
            throw new RuntimeException("Un produit avec ce nom existe déjà");
        }

        Produit produit = produitMapper.toEntity(request);
        Produit saved = produitRepository.save(produit);
        return produitMapper.toResponse(saved);
    }

    public ProduitResponse updateProduit(Long id, ProduitRequest request) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        produitMapper.updateEntityFromRequest(request, produit);
        Produit updated = produitRepository.save(produit);
        return produitMapper.toResponse(updated);
    }

    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé");
        }
        produitRepository.deleteById(id);
    }

    public ProduitResponse getProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        return produitMapper.toResponse(produit);
    }

    public List<ProduitResponse> getAllProduits() {
        return produitRepository.findAll().stream()
                .map(produitMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Méthode utilitaire pour les autres services
    public Produit getProduitEntityById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }
}