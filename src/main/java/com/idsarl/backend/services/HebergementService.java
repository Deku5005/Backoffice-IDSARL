package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.HebergementRequest;
import com.idsarl.backend.dto.response.HebergementResponse;
import com.idsarl.backend.Entite.Hebergement;
import com.idsarl.backend.mapper.HebergementMapper;
import com.idsarl.backend.repositories.HebergementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HebergementService {

    private final HebergementRepository hebergementRepository;
    private final HebergementMapper hebergementMapper;
    private final ProduitService produitService;

    public HebergementResponse createHebergement(HebergementRequest request) {
        Hebergement hebergement = hebergementMapper.toEntity(request);

        if (request.getProduitId() != null) {
            hebergement.setProduit(produitService.getProduitEntityById(request.getProduitId()));
        }

        Hebergement saved = hebergementRepository.save(hebergement);
        return hebergementMapper.toResponse(saved);
    }

    public HebergementResponse updateHebergement(Long id, HebergementRequest request) {
        Hebergement hebergement = hebergementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hébergement non trouvé"));

        hebergementMapper.updateEntityFromRequest(request, hebergement);

        if (request.getProduitId() != null) {
            hebergement.setProduit(produitService.getProduitEntityById(request.getProduitId()));
        } else {
            hebergement.setProduit(null);
        }

        Hebergement updated = hebergementRepository.save(hebergement);
        return hebergementMapper.toResponse(updated);
    }

    public void deleteHebergement(Long id) {
        if (!hebergementRepository.existsById(id)) {
            throw new RuntimeException("Hébergement non trouvé");
        }
        hebergementRepository.deleteById(id);
    }

    public HebergementResponse getHebergementById(Long id) {
        Hebergement hebergement = hebergementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hébergement non trouvé"));
        return hebergementMapper.toResponse(hebergement);
    }

    public List<HebergementResponse> getAllHebergements() {
        return hebergementRepository.findAll().stream()
                .map(hebergementMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<HebergementResponse> getHebergementsExpirantBientot(int jours) {
        LocalDate now = LocalDate.now();
        return hebergementRepository.findExpiringSoon(now, jours).stream()
                .map(hebergementMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<HebergementResponse> getHebergementsByProduit(Long produitId) {
        return hebergementRepository.findByProduitId(produitId).stream()
                .map(hebergementMapper::toResponse)
                .collect(Collectors.toList());
    }
}