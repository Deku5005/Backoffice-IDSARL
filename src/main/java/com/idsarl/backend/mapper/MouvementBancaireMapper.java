package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.MouvementBancaireRequest;
import com.idsarl.backend.dto.response.MouvementBancaireResponse;
import com.idsarl.backend.Entite.MouvementBancaire;
import org.springframework.stereotype.Component;

@Component
public class MouvementBancaireMapper {

    public MouvementBancaireResponse toResponse(MouvementBancaire mouvement) {
        if (mouvement == null) return null;
        MouvementBancaireResponse response = new MouvementBancaireResponse();
        response.setId(mouvement.getId());
        response.setCompteId(mouvement.getCompte() != null ? mouvement.getCompte().getId() : null);
        response.setType(mouvement.getType());
        response.setMontant(mouvement.getMontant());
        response.setDate(mouvement.getDate());
        response.setLibelle(mouvement.getLibelle());
        response.setProjetId(mouvement.getProjet() != null ? mouvement.getProjet().getId() : null);
        return response;
    }

    public MouvementBancaire toEntity(MouvementBancaireRequest request) {
        if (request == null) return null;
        MouvementBancaire mouvement = new MouvementBancaire();
        mouvement.setType(request.getType());
        mouvement.setMontant(request.getMontant());
        mouvement.setDate(request.getDate());
        mouvement.setLibelle(request.getLibelle());
        // compte et projet seront settés dans le service
        return mouvement;
    }

    public void updateEntityFromRequest(MouvementBancaireRequest request, MouvementBancaire mouvement) {
        if (request == null || mouvement == null) return;
        mouvement.setType(request.getType());
        mouvement.setMontant(request.getMontant());
        mouvement.setDate(request.getDate());
        mouvement.setLibelle(request.getLibelle());
        // compte et projet seront mis à jour dans le service
    }
}