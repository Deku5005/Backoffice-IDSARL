package com.idsarl.backend.dto.response;

import com.idsarl.backend.Enum.Role;
import com.idsarl.backend.Enum.StatutUtilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurResponse {

    private Long id;
    private String nom;
    private String email;
    private Role role;
    private StatutUtilisateur statut;
    private LocalDateTime dateCreation;
}