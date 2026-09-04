package com.idsarl.backend.config;

import com.idsarl.backend.Entite.Utilisateur;
import com.idsarl.backend.Enum.Role;
import com.idsarl.backend.Enum.StatutUtilisateur;
import com.idsarl.backend.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Créer l'utilisateur ADMIN par défaut s'il n'existe pas
        if (!utilisateurRepository.existsByEmail("admin@idsarl.com")) {
            Utilisateur admin = Utilisateur.builder()
                    .nom("Administrateur")
                    .email("admin@idsarl.com")
                    .motDePasse(passwordEncoder.encode("Admin123!"))
                    .role(Role.ADMIN)
                    .statut(StatutUtilisateur.ACTIF)
                    .build();

            utilisateurRepository.save(admin);
            log.info("✅ Utilisateur ADMIN créé par défaut (email: admin@idsarl.com, mot de passe: Admin123!)");
        } else {
            log.info("⚠️ L'utilisateur ADMIN existe déjà");
        }

        // Créer des utilisateurs supplémentaires pour les tests (optionnel)
        createUserIfNotExists("finance@idsarl.com", "Finance", Role.FINANCE);
        createUserIfNotExists("commercial@idsarl.com", "Commercial", Role.COMMERCIAL);
        createUserIfNotExists("dev@idsarl.com", "Développeur", Role.DEVELOPPEUR);
        createUserIfNotExists("consult@idsarl.com", "Consultation", Role.CONSULTATION);

        log.info("✅ Initialisation des données terminée");
    }

    private void createUserIfNotExists(String email, String nom, Role role) {
        if (!utilisateurRepository.existsByEmail(email)) {
            Utilisateur user = Utilisateur.builder()
                    .nom(nom)
                    .email(email)
                    .motDePasse(passwordEncoder.encode("Test123!"))
                    .role(role)
                    .statut(StatutUtilisateur.ACTIF)
                    .build();
            utilisateurRepository.save(user);
            log.info("✅ Utilisateur {} créé (email: {})", nom, email);
        }
    }
}