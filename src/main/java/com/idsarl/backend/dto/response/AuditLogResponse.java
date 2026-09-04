package com.idsarl.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogResponse {

    private Long id;
    private Long utilisateurId;
    private String utilisateurNom;
    private String action;
    private String module;
    private String details;
    private LocalDateTime dateAction;
}