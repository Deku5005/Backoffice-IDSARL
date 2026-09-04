package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.DeploiementRequest;
import com.idsarl.backend.dto.response.DeploiementResponse;
import com.idsarl.backend.Entite.Deploiement;
import org.springframework.stereotype.Component;

@Component
public class DeploiementMapper {

    public DeploiementResponse toResponse(Deploiement deploiement) {
        if (deploiement == null) return null;
        DeploiementResponse response = new DeploiementResponse();
        response.setId(deploiement.getId());
        response.setProduitId(deploiement.getProduit() != null ? deploiement.getProduit().getId() : null);
        response.setProduitNom(deploiement.getProduit() != null ? deploiement.getProduit().getNom() : null);
        response.setClientId(deploiement.getClient() != null ? deploiement.getClient().getId() : null);
        response.setClientNom(deploiement.getClient() != null ? deploiement.getClient().getNom() : null);
        response.setDateDeploiement(deploiement.getDateDeploiement());
        response.setStatut(deploiement.getStatut());
        response.setMontant(deploiement.getMontant());
        response.setPeriodicite(deploiement.getPeriodicite());
        return response;
    }

    public Deploiement toEntity(DeploiementRequest request) {
        if (request == null) return null;
        Deploiement deploiement = new Deploiement();
        deploiement.setDateDeploiement(request.getDateDeploiement());
        deploiement.setStatut(request.getStatut());
        deploiement.setMontant(request.getMontant());
        deploiement.setPeriodicite(request.getPeriodicite());
        // produit et client seront settés dans le service
        return deploiement;
    }

    public void updateEntityFromRequest(DeploiementRequest request, Deploiement deploiement) {
        if (request == null || deploiement == null) return;
        deploiement.setDateDeploiement(request.getDateDeploiement());
        deploiement.setStatut(request.getStatut());
        deploiement.setMontant(request.getMontant());
        deploiement.setPeriodicite(request.getPeriodicite());
        // produit et client seront mis à jour dans le service
    }
}