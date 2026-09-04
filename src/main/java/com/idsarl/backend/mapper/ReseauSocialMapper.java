package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.ReseauSocialRequest;
import com.idsarl.backend.dto.response.ReseauSocialResponse;
import com.idsarl.backend.Entite.ReseauSocial;
import org.springframework.stereotype.Component;

@Component
public class ReseauSocialMapper {

    public ReseauSocialResponse toResponse(ReseauSocial reseau) {
        if (reseau == null) return null;
        ReseauSocialResponse response = new ReseauSocialResponse();
        response.setId(reseau.getId());
        response.setPlateforme(reseau.getPlateforme());
        response.setIdentifiant(reseau.getIdentifiant());
        response.setResponsable(reseau.getResponsable());
        response.setMotDePasse(reseau.getMotDePasse());
        response.setStatut(reseau.getStatut());
        return response;
    }

    public ReseauSocial toEntity(ReseauSocialRequest request) {
        if (request == null) return null;
        ReseauSocial reseau = new ReseauSocial();
        reseau.setPlateforme(request.getPlateforme());
        reseau.setIdentifiant(request.getIdentifiant());
        reseau.setResponsable(request.getResponsable());
        reseau.setMotDePasse(request.getMotDePasse());
        reseau.setStatut(request.getStatut());
        return reseau;
    }

    public void updateEntityFromRequest(ReseauSocialRequest request, ReseauSocial reseau) {
        if (request == null || reseau == null) return;
        reseau.setPlateforme(request.getPlateforme());
        reseau.setIdentifiant(request.getIdentifiant());
        reseau.setResponsable(request.getResponsable());
        reseau.setMotDePasse(request.getMotDePasse());
        reseau.setStatut(request.getStatut());
    }
}