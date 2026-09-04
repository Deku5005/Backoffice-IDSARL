package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.CompteBancaireRequest;
import com.idsarl.backend.dto.response.CompteBancaireResponse;
import com.idsarl.backend.Entite.CompteBancaire;
import org.springframework.stereotype.Component;

@Component
public class CompteBancaireMapper {

    public CompteBancaireResponse toResponse(CompteBancaire compte) {
        if (compte == null) return null;
        CompteBancaireResponse response = new CompteBancaireResponse();
        response.setId(compte.getId());
        response.setBanque(compte.getBanque());
        response.setNumero(compte.getNumero());
        response.setDevise(compte.getDevise());
        response.setSolde(compte.getSolde());
        return response;
    }

    public CompteBancaire toEntity(CompteBancaireRequest request) {
        if (request == null) return null;
        CompteBancaire compte = new CompteBancaire();
        compte.setBanque(request.getBanque());
        compte.setNumero(request.getNumero());
        compte.setDevise(request.getDevise());
        compte.setSolde(request.getSolde());
        return compte;
    }

    public void updateEntityFromRequest(CompteBancaireRequest request, CompteBancaire compte) {
        if (request == null || compte == null) return;
        compte.setBanque(request.getBanque());
        compte.setNumero(request.getNumero());
        compte.setDevise(request.getDevise());
        compte.setSolde(request.getSolde());
    }
}