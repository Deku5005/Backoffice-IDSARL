package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.MouvementBancaireRequest;
import com.idsarl.backend.dto.response.MouvementBancaireResponse;
import com.idsarl.backend.Entite.MouvementBancaire;
import com.idsarl.backend.Entite.Projet;
import com.idsarl.backend.Enum.TypeMouvement;
import com.idsarl.backend.mapper.MouvementBancaireMapper;
import com.idsarl.backend.repositories.MouvementBancaireRepository;
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
public class MouvementBancaireService {

    private final MouvementBancaireRepository mouvementBancaireRepository;
    private final MouvementBancaireMapper mouvementBancaireMapper;
    private final CompteBancaireService compteBancaireService;
    private final ProjetService projetService;

    public MouvementBancaireResponse createMouvement(MouvementBancaireRequest request) {
        MouvementBancaire mouvement = mouvementBancaireMapper.toEntity(request);

        // Récupérer le compte
        mouvement.setCompte(compteBancaireService.getCompteEntityById(request.getCompteId()));

        // Récupérer le projet si présent
        if (request.getProjetId() != null) {
            Projet projet = projetService.getProjetEntityById(request.getProjetId());
            mouvement.setProjet(projet);
        }

        // Mettre à jour le solde du compte
        BigDecimal montant = request.getMontant();
        if (request.getType() == TypeMouvement.SORTIE) {
            montant = montant.negate();
        }
        compteBancaireService.updateSolde(request.getCompteId(), montant);

        MouvementBancaire saved = mouvementBancaireRepository.save(mouvement);
        return mouvementBancaireMapper.toResponse(saved);
    }

    public MouvementBancaireResponse updateMouvement(Long id, MouvementBancaireRequest request) {
        MouvementBancaire mouvement = mouvementBancaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement bancaire non trouvé"));

        // Annuler l'ancien mouvement sur le solde
        BigDecimal oldMontant = mouvement.getMontant();
        if (mouvement.getType() == TypeMouvement.SORTIE) {
            oldMontant = oldMontant.negate();
        }
        compteBancaireService.updateSolde(mouvement.getCompte().getId(), oldMontant.negate());

        // Mettre à jour le mouvement
        mouvementBancaireMapper.updateEntityFromRequest(request, mouvement);
        mouvement.setCompte(compteBancaireService.getCompteEntityById(request.getCompteId()));

        if (request.getProjetId() != null) {
            Projet projet = projetService.getProjetEntityById(request.getProjetId());
            mouvement.setProjet(projet);
        } else {
            mouvement.setProjet(null);
        }

        // Appliquer le nouveau mouvement sur le solde
        BigDecimal newMontant = request.getMontant();
        if (request.getType() == TypeMouvement.SORTIE) {
            newMontant = newMontant.negate();
        }
        compteBancaireService.updateSolde(request.getCompteId(), newMontant);

        MouvementBancaire updated = mouvementBancaireRepository.save(mouvement);
        return mouvementBancaireMapper.toResponse(updated);
    }

    public void deleteMouvement(Long id) {
        MouvementBancaire mouvement = mouvementBancaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement bancaire non trouvé"));

        // Annuler le mouvement sur le solde
        BigDecimal montant = mouvement.getMontant();
        if (mouvement.getType() == TypeMouvement.SORTIE) {
            montant = montant.negate();
        }
        compteBancaireService.updateSolde(mouvement.getCompte().getId(), montant.negate());

        mouvementBancaireRepository.deleteById(id);
    }

    // Méthodes de lecture
    public MouvementBancaireResponse getMouvementById(Long id) {
        MouvementBancaire mouvement = mouvementBancaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mouvement bancaire non trouvé"));
        return mouvementBancaireMapper.toResponse(mouvement);
    }

    public List<MouvementBancaireResponse> getMouvementsByCompte(Long compteId) {
        return mouvementBancaireRepository.findByCompteId(compteId).stream()
                .map(mouvementBancaireMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<MouvementBancaireResponse> getMouvementsByDateRange(LocalDate debut, LocalDate fin) {
        return mouvementBancaireRepository.findByDateBetween(debut, fin).stream()
                .map(mouvementBancaireMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalEntrees(Long compteId) {
        return mouvementBancaireRepository.sumMontantByCompteAndType(compteId, TypeMouvement.ENTREE);
    }

    public BigDecimal getTotalSorties(Long compteId) {
        return mouvementBancaireRepository.sumMontantByCompteAndType(compteId, TypeMouvement.SORTIE);
    }
}