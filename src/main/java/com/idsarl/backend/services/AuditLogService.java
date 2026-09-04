package com.idsarl.backend.services;

import com.idsarl.backend.dto.response.AuditLogResponse;
import com.idsarl.backend.Entite.AuditLog;
import com.idsarl.backend.mapper.AuditLogMapper;
import com.idsarl.backend.repositories.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    public List<AuditLogResponse> getAllLogs() {
        return auditLogRepository.findAll().stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AuditLogResponse> getLogsByUtilisateur(Long utilisateurId) {
        return auditLogRepository.findByUtilisateurId(utilisateurId).stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AuditLogResponse> getLogsByModule(String module) {
        return auditLogRepository.findByModule(module).stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AuditLogResponse> getLogsBetweenDates(LocalDateTime debut, LocalDateTime fin) {
        return auditLogRepository.findByDateActionBetween(debut, fin).stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    public long countLogsByModuleSince(String module, LocalDateTime since) {
        return auditLogRepository.countByModuleSince(module, since);
    }
}