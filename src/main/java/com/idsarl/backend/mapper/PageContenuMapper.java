package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.request.PageContenuRequest;
import com.idsarl.backend.dto.response.PageContenuResponse;
import com.idsarl.backend.Entite.PageContenu;
import org.springframework.stereotype.Component;

@Component
public class PageContenuMapper {

    public PageContenuResponse toResponse(PageContenu page) {
        if (page == null) return null;
        PageContenuResponse response = new PageContenuResponse();
        response.setId(page.getId());
        response.setType(page.getType());
        response.setTitre(page.getTitre());
        response.setContenu(page.getContenu());
        response.setDatePublication(page.getDatePublication());
        return response;
    }

    public PageContenu toEntity(PageContenuRequest request) {
        if (request == null) return null;
        PageContenu page = new PageContenu();
        page.setType(request.getType());
        page.setTitre(request.getTitre());
        page.setContenu(request.getContenu());
        // datePublication sera gérée par JPA
        return page;
    }

    public void updateEntityFromRequest(PageContenuRequest request, PageContenu page) {
        if (request == null || page == null) return;
        page.setType(request.getType());
        page.setTitre(request.getTitre());
        page.setContenu(request.getContenu());
    }
}