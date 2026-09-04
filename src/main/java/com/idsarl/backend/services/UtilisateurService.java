package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.UtilisateurRequest;
import com.idsarl.backend.dto.response.UtilisateurResponse;
import com.idsarl.backend.Entite.Utilisateur;
import com.idsarl.backend.Enum.Role;
import com.idsarl.backend.Enum.StatutUtilisateur;
import com.idsarl.backend.mapper.UtilisateurMapper;
import com.idsarl.backend.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final PasswordEncoder passwordEncoder;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Z])(?=.*\\d).{8,}$"
    );

    // UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + email));

        return User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())
                .roles(utilisateur.getRole().name())
                .build();
    }

    // CRUD
    public UtilisateurResponse createUtilisateur(UtilisateurRequest request) {
        validatePassword(request.getMotDePasse());

        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà");
        }

        Utilisateur utilisateur = utilisateurMapper.toEntity(request);
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setStatut(StatutUtilisateur.ACTIF);

        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toResponse(saved);
    }

    public UtilisateurResponse updateUtilisateur(Long id, UtilisateurRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier que l'email n'est pas déjà utilisé par un autre utilisateur
        if (!utilisateur.getEmail().equals(request.getEmail()) &&
                utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        utilisateurMapper.updateEntityFromRequest(request, utilisateur);

        if (request.getMotDePasse() != null && !request.getMotDePasse().isEmpty()) {
            validatePassword(request.getMotDePasse());
            utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        }

        Utilisateur updated = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toResponse(updated);
    }

    public void deleteUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé");
        }
        utilisateurRepository.deleteById(id);
    }

    // Méthodes de lecture
    public UtilisateurResponse getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return utilisateurMapper.toResponse(utilisateur);
    }

    public UtilisateurResponse getUtilisateurByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return utilisateurMapper.toResponse(utilisateur);
    }

    public List<UtilisateurResponse> getAllUtilisateurs() {
        return utilisateurRepository.findAll().stream()
                .map(utilisateurMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<UtilisateurResponse> getUtilisateursByStatut(StatutUtilisateur statut) {
        return utilisateurRepository.findByStatut(statut).stream()
                .map(utilisateurMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Méthodes utilitaires
    private void validatePassword(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new RuntimeException(
                    "Le mot de passe doit contenir au moins 8 caractères, 1 majuscule et 1 chiffre"
            );
        }
    }

    public Utilisateur getUtilisateurEntityByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur getUtilisateurEntityById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
}