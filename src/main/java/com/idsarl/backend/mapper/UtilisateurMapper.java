package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.UtilisateurRequest;
import com.idsarl.backend.dto.response.UtilisateurResponse;
import com.idsarl.backend.Entite.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurResponse toResponse(Utilisateur utilisateur) {
        if (utilisateur == null) return null;
        UtilisateurResponse response = new UtilisateurResponse();
        response.setId(utilisateur.getId());
        response.setNom(utilisateur.getNom());
        response.setEmail(utilisateur.getEmail());
        response.setRole(utilisateur.getRole());
        response.setStatut(utilisateur.getStatut());
        response.setDateCreation(utilisateur.getDateCreation());
        return response;
    }

    public Utilisateur toEntity(UtilisateurRequest request) {
        if (request == null) return null;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(request.getMotDePasse());
        utilisateur.setRole(request.getRole());
        utilisateur.setStatut(request.getStatut());
        return utilisateur;
    }

    public void updateEntityFromRequest(UtilisateurRequest request, Utilisateur utilisateur) {
        if (request == null || utilisateur == null) return;
        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(request.getRole());
        utilisateur.setStatut(request.getStatut());
        // Ne pas mettre à jour le mot de passe ici (fait dans le service)
    }
}