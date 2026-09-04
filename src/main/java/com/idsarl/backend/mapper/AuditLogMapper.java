package com.idsarl.backend.mapper;

import com.idsarl.backend.dto.response.AuditLogResponse;
import com.idsarl.backend.Entite.AuditLog;
import org.springframework.stereotype.Component;

@Component
public class AuditLogMapper {

    public AuditLogResponse toResponse(AuditLog auditLog) {
        if (auditLog == null) return null;
        AuditLogResponse response = new AuditLogResponse();
        response.setId(auditLog.getId());
        response.setUtilisateurId(auditLog.getUtilisateurId());
        response.setUtilisateurNom(auditLog.getUtilisateurNom());
        response.setAction(auditLog.getAction());
        response.setModule(auditLog.getModule());
        response.setDetails(auditLog.getDetails());
        response.setDateAction(auditLog.getDateAction());
        return response;
    }
}