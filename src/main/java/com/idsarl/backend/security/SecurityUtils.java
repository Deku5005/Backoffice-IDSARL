package com.idsarl.backend.security;

import com.idsarl.backend.Entite.Utilisateur;
import com.idsarl.backend.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UtilisateurRepository utilisateurRepository;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }
            return principal.toString();
        }
        return null;
    }

    public Long getCurrentUserId() {
        String email = getCurrentUsername();
        if (email != null) {
            return utilisateurRepository.findByEmail(email)
                    .map(Utilisateur::getId)
                    .orElse(null);
        }
        return null;
    }

    public Utilisateur getCurrentUtilisateur() {
        String email = getCurrentUsername();
        if (email != null) {
            return utilisateurRepository.findByEmail(email).orElse(null);
        }
        return null;
    }
}