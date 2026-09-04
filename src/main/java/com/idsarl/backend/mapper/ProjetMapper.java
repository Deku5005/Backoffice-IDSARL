package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.ProjetRequest;
import com.idsarl.backend.dto.response.ProjetResponse;
import com.idsarl.backend.Entite.Projet;
import org.springframework.stereotype.Component;

@Component
public class ProjetMapper {

    public ProjetResponse toResponse(Projet projet) {
        if (projet == null) return null;
        ProjetResponse response = new ProjetResponse();
        response.setId(projet.getId());
        response.setNom(projet.getNom());
        response.setStatut(projet.getStatut());
        response.setDateDebut(projet.getDateDebut());
        response.setDateFin(projet.getDateFin());
        response.setMontantApporte(projet.getMontantApporte());
        response.setDepenses(projet.getDepenses());
        // Calcul de la marge
        response.setMarge(projet.getMontantApporte().subtract(projet.getDepenses()));
        return response;
    }

    public Projet toEntity(ProjetRequest request) {
        if (request == null) return null;
        Projet projet = new Projet();
        projet.setNom(request.getNom());
        projet.setStatut(request.getStatut());
        projet.setDateDebut(request.getDateDebut());
        projet.setDateFin(request.getDateFin());
        projet.setMontantApporte(request.getMontantApporte());
        projet.setDepenses(request.getDepenses());
        return projet;
    }

    public void updateEntityFromRequest(ProjetRequest request, Projet projet) {
        if (request == null || projet == null) return;
        projet.setNom(request.getNom());
        projet.setStatut(request.getStatut());
        projet.setDateDebut(request.getDateDebut());
        projet.setDateFin(request.getDateFin());
        projet.setMontantApporte(request.getMontantApporte());
        projet.setDepenses(request.getDepenses());
    }
}