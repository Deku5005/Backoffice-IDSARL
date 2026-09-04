package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.ProjetRequest;
import com.idsarl.backend.dto.response.ProjetResponse;
import com.idsarl.backend.Entite.Projet;
import com.idsarl.backend.Enum.StatutProjet;
import com.idsarl.backend.mapper.ProjetMapper;
import com.idsarl.backend.repositories.ProjetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjetService {

    private final ProjetRepository projetRepository;
    private final ProjetMapper projetMapper;

    public ProjetResponse createProjet(ProjetRequest request) {
        Projet projet = projetMapper.toEntity(request);
        Projet saved = projetRepository.save(projet);
        return projetMapper.toResponse(saved);
    }

    public ProjetResponse updateProjet(Long id, ProjetRequest request) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));

        projetMapper.updateEntityFromRequest(request, projet);
        Projet updated = projetRepository.save(projet);
        return projetMapper.toResponse(updated);
    }

    public void deleteProjet(Long id) {
        if (!projetRepository.existsById(id)) {
            throw new RuntimeException("Projet non trouvé");
        }
        projetRepository.deleteById(id);
    }

    public ProjetResponse getProjetById(Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        return projetMapper.toResponse(projet);
    }

    public List<ProjetResponse> getAllProjets() {
        return projetRepository.findAll().stream()
                .map(projetMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProjetResponse> getProjetsByStatut(StatutProjet statut) {
        return projetRepository.findByStatut(statut).stream()
                .map(projetMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ProjetResponse> getProjetsEnRetard() {
        LocalDate today = LocalDate.now();
        return projetRepository.findProjetsEnRetard(today).stream()
                .map(projetMapper::toResponse)
                .collect(Collectors.toList());
    }

    public long countProjetsEnRetard() {
        return projetRepository.countProjetsEnRetard(LocalDate.now());
    }

    public BigDecimal getTotalMontantApporte() {
        return projetRepository.sumMontantApporte();
    }

    public BigDecimal getTotalDepenses() {
        return projetRepository.sumDepenses();
    }

    // Méthode utilitaire
    public Projet getProjetEntityById(Long id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
    }
}