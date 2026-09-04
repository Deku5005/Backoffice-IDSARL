package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.HebergementRequest;
import com.idsarl.backend.dto.response.HebergementResponse;
import com.idsarl.backend.Entite.Hebergement;
import org.springframework.stereotype.Component;

@Component
public class HebergementMapper {

    public HebergementResponse toResponse(Hebergement hebergement) {
        if (hebergement == null) return null;
        HebergementResponse response = new HebergementResponse();
        response.setId(hebergement.getId());
        response.setPlateforme(hebergement.getPlateforme());
        response.setDomaine(hebergement.getDomaine());
        response.setDateExpiration(hebergement.getDateExpiration());
        response.setCout(hebergement.getCout());
        response.setProduitId(hebergement.getProduit() != null ? hebergement.getProduit().getId() : null);
        response.setProduitNom(hebergement.getProduit() != null ? hebergement.getProduit().getNom() : null);
        return response;
    }

    public Hebergement toEntity(HebergementRequest request) {
        if (request == null) return null;
        Hebergement hebergement = new Hebergement();
        hebergement.setPlateforme(request.getPlateforme());
        hebergement.setDomaine(request.getDomaine());
        hebergement.setDateExpiration(request.getDateExpiration());
        hebergement.setCout(request.getCout());
        // produit sera setté dans le service
        return hebergement;
    }

    public void updateEntityFromRequest(HebergementRequest request, Hebergement hebergement) {
        if (request == null || hebergement == null) return;
        hebergement.setPlateforme(request.getPlateforme());
        hebergement.setDomaine(request.getDomaine());
        hebergement.setDateExpiration(request.getDateExpiration());
        hebergement.setCout(request.getCout());
        // produit sera mis à jour dans le service
    }
}