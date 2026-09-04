package com.idsarl.backend.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HebergementRequest {

    @NotBlank(message = "La plateforme est obligatoire")
    private String plateforme;

    @NotBlank(message = "Le domaine est obligatoire")
    private String domaine;

    @NotNull(message = "La date d'expiration est obligatoire")
    @Future(message = "La date d'expiration doit être dans le futur")
    private LocalDate dateExpiration;

    @NotNull(message = "Le coût est obligatoire")
    @Positive(message = "Le coût doit être positif")
    private BigDecimal cout;

    private Long produitId;  // Optionnel
}