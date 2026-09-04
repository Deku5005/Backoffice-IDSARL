package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.DeploiementRequest;
import com.idsarl.backend.dto.response.DeploiementResponse;
import com.idsarl.backend.Entite.Deploiement;
import com.idsarl.backend.mapper.DeploiementMapper;
import com.idsarl.backend.repositories.DeploiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeploiementService {

    private final DeploiementRepository deploiementRepository;
    private final DeploiementMapper deploiementMapper;
    private final ProduitService produitService;
    private final ClientService clientService;

    public DeploiementResponse createDeploiement(DeploiementRequest request) {
        Deploiement deploiement = deploiementMapper.toEntity(request);
        deploiement.setProduit(produitService.getProduitEntityById(request.getProduitId()));
        deploiement.setClient(clientService.getClientEntityById(request.getClientId()));

        Deploiement saved = deploiementRepository.save(deploiement);
        return deploiementMapper.toResponse(saved);
    }

    public DeploiementResponse updateDeploiement(Long id, DeploiementRequest request) {
        Deploiement deploiement = deploiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Déploiement non trouvé"));

        deploiementMapper.updateEntityFromRequest(request, deploiement);
        deploiement.setProduit(produitService.getProduitEntityById(request.getProduitId()));
        deploiement.setClient(clientService.getClientEntityById(request.getClientId()));

        Deploiement updated = deploiementRepository.save(deploiement);
        return deploiementMapper.toResponse(updated);
    }

    public void deleteDeploiement(Long id) {
        if (!deploiementRepository.existsById(id)) {
            throw new RuntimeException("Déploiement non trouvé");
        }
        deploiementRepository.deleteById(id);
    }

    public DeploiementResponse getDeploiementById(Long id) {
        Deploiement deploiement = deploiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Déploiement non trouvé"));
        return deploiementMapper.toResponse(deploiement);
    }

    public List<DeploiementResponse> getAllDeploiements() {
        return deploiementRepository.findAll().stream()
                .map(deploiementMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<DeploiementResponse> getDeploiementsByClient(Long clientId) {
        return deploiementRepository.findByClientId(clientId).stream()
                .map(deploiementMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<DeploiementResponse> getDeploiementsByProduit(Long produitId) {
        return deploiementRepository.findByProduitId(produitId).stream()
                .map(deploiementMapper::toResponse)
                .collect(Collectors.toList());
    }
}