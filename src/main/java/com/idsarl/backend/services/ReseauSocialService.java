package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.ReseauSocialRequest;
import com.idsarl.backend.dto.response.ReseauSocialResponse;
import com.idsarl.backend.Entite.ReseauSocial;
import com.idsarl.backend.mapper.ReseauSocialMapper;
import com.idsarl.backend.repositories.ReseauSocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReseauSocialService {

    private final ReseauSocialRepository reseauSocialRepository;
    private final ReseauSocialMapper reseauSocialMapper;

    public ReseauSocialResponse createReseau(ReseauSocialRequest request) {
        if (reseauSocialRepository.existsByPlateformeAndIdentifiant(
                request.getPlateforme(), request.getIdentifiant())) {
            throw new RuntimeException("Ce compte existe déjà pour cette plateforme");
        }

        ReseauSocial reseau = reseauSocialMapper.toEntity(request);
        ReseauSocial saved = reseauSocialRepository.save(reseau);
        return reseauSocialMapper.toResponse(saved);
    }

    public ReseauSocialResponse updateReseau(Long id, ReseauSocialRequest request) {
        ReseauSocial reseau = reseauSocialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte réseau social non trouvé"));

        reseauSocialMapper.updateEntityFromRequest(request, reseau);
        ReseauSocial updated = reseauSocialRepository.save(reseau);
        return reseauSocialMapper.toResponse(updated);
    }

    public void deleteReseau(Long id) {
        if (!reseauSocialRepository.existsById(id)) {
            throw new RuntimeException("Compte réseau social non trouvé");
        }
        reseauSocialRepository.deleteById(id);
    }

    public ReseauSocialResponse getReseauById(Long id) {
        ReseauSocial reseau = reseauSocialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte réseau social non trouvé"));
        return reseauSocialMapper.toResponse(reseau);
    }

    public List<ReseauSocialResponse> getAllReseaux() {
        return reseauSocialRepository.findAll().stream()
                .map(reseauSocialMapper::toResponse)
                .collect(Collectors.toList());
    }
}