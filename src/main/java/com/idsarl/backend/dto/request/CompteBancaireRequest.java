package com.idsarl.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteBancaireRequest {

    @NotBlank(message = "La banque est obligatoire")
    private String banque;

    @NotBlank(message = "Le numéro de compte est obligatoire")
    private String numero;

    @NotBlank(message = "La devise est obligatoire")
    private String devise;

    @NotNull(message = "Le solde est obligatoire")
    @PositiveOrZero(message = "Le solde doit être positif ou nul")
    private BigDecimal solde;
}