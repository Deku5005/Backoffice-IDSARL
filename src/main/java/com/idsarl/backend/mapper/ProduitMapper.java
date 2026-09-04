package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.ProduitRequest;
import com.idsarl.backend.dto.response.ProduitResponse;
import com.idsarl.backend.Entite.Produit;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper {

    public ProduitResponse toResponse(Produit produit) {
        if (produit == null) return null;
        ProduitResponse response = new ProduitResponse();
        response.setId(produit.getId());
        response.setNom(produit.getNom());
        response.setDescription(produit.getDescription());
        response.setTechnologie(produit.getTechnologie());
        response.setStatut(produit.getStatut());
        response.setHistoriqueVersions(produit.getHistoriqueVersions());
        return response;
    }

    public Produit toEntity(ProduitRequest request) {
        if (request == null) return null;
        Produit produit = new Produit();
        produit.setNom(request.getNom());
        produit.setDescription(request.getDescription());
        produit.setTechnologie(request.getTechnologie());
        produit.setStatut(request.getStatut());
        produit.setHistoriqueVersions(request.getHistoriqueVersions());
        return produit;
    }

    public void updateEntityFromRequest(ProduitRequest request, Produit produit) {
        if (request == null || produit == null) return;
        produit.setNom(request.getNom());
        produit.setDescription(request.getDescription());
        produit.setTechnologie(request.getTechnologie());
        produit.setStatut(request.getStatut());
        produit.setHistoriqueVersions(request.getHistoriqueVersions());
    }
}