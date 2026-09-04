package com.idsarl.backend.services;

import com.idsarl.backend.dto.request.PageContenuRequest;
import com.idsarl.backend.dto.response.PageContenuResponse;
import com.idsarl.backend.Entite.PageContenu;
import com.idsarl.backend.Enum.TypePageContenu;
import com.idsarl.backend.mapper.PageContenuMapper;
import com.idsarl.backend.repositories.PageContenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PageContenuService {

    private final PageContenuRepository pageContenuRepository;
    private final PageContenuMapper pageContenuMapper;

    public PageContenuResponse createPage(PageContenuRequest request) {
        PageContenu page = pageContenuMapper.toEntity(request);
        PageContenu saved = pageContenuRepository.save(page);
        return pageContenuMapper.toResponse(saved);
    }

    public PageContenuResponse updatePage(Long id, PageContenuRequest request) {
        PageContenu page = pageContenuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Page non trouvée"));

        pageContenuMapper.updateEntityFromRequest(request, page);
        PageContenu updated = pageContenuRepository.save(page);
        return pageContenuMapper.toResponse(updated);
    }

    public void deletePage(Long id) {
        if (!pageContenuRepository.existsById(id)) {
            throw new RuntimeException("Page non trouvée");
        }
        pageContenuRepository.deleteById(id);
    }

    public PageContenuResponse getPageById(Long id) {
        PageContenu page = pageContenuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Page non trouvée"));
        return pageContenuMapper.toResponse(page);
    }

    public PageContenuResponse getPageByType(TypePageContenu type) {
        PageContenu page = pageContenuRepository.findByType(type)
                .orElseThrow(() -> new RuntimeException("Page non trouvée pour ce type"));
        return pageContenuMapper.toResponse(page);
    }

    public List<PageContenuResponse> getAllPages() {
        return pageContenuRepository.findAll().stream()
                .map(pageContenuMapper::toResponse)
                .collect(Collectors.toList());
    }
}