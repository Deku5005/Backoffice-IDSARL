package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.CompteBancaireRequest;
import com.idsarl.backend.dto.response.CompteBancaireResponse;
import com.idsarl.backend.Entite.CompteBancaire;
import com.idsarl.backend.mapper.CompteBancaireMapper;
import com.idsarl.backend.repositories.CompteBancaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompteBancaireService {

    private final CompteBancaireRepository compteBancaireRepository;
    private final CompteBancaireMapper compteBancaireMapper;

    // CRUD
    public CompteBancaireResponse createCompte(CompteBancaireRequest request) {
        CompteBancaire compte = compteBancaireMapper.toEntity(request);
        compte.setSolde(BigDecimal.ZERO);  // Initialisation à 0
        CompteBancaire saved = compteBancaireRepository.save(compte);
        return compteBancaireMapper.toResponse(saved);
    }

    public CompteBancaireResponse updateCompte(Long id, CompteBancaireRequest request) {
        CompteBancaire compte = compteBancaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte bancaire non trouvé"));

        compteBancaireMapper.updateEntityFromRequest(request, compte);
        CompteBancaire updated = compteBancaireRepository.save(compte);
        return compteBancaireMapper.toResponse(updated);
    }

    public void deleteCompte(Long id) {
        if (!compteBancaireRepository.existsById(id)) {
            throw new RuntimeException("Compte bancaire non trouvé");
        }
        compteBancaireRepository.deleteById(id);
    }

    // Méthodes de lecture
    public CompteBancaireResponse getCompteById(Long id) {
        CompteBancaire compte = compteBancaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte bancaire non trouvé"));
        return compteBancaireMapper.toResponse(compte);
    }

    public CompteBancaireResponse getCompteByNumero(String numero) {
        CompteBancaire compte = compteBancaireRepository.findByNumero(numero)
                .orElseThrow(() -> new RuntimeException("Compte bancaire non trouvé"));
        return compteBancaireMapper.toResponse(compte);
    }

    public List<CompteBancaireResponse> getAllComptes() {
        return compteBancaireRepository.findAll().stream()
                .map(compteBancaireMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Méthodes utilitaires
    public CompteBancaire getCompteEntityById(Long id) {
        return compteBancaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte bancaire non trouvé"));
    }

    public void updateSolde(Long compteId, BigDecimal montant) {
        CompteBancaire compte = getCompteEntityById(compteId);
        compte.setSolde(compte.getSolde().add(montant));
        compteBancaireRepository.save(compte);
    }
}