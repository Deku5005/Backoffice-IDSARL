package com.idsarl.backend.dto.request;

import com.idsarl.backend.Enum.TypeMouvement;
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
public class MouvementBancaireRequest {

    @NotNull(message = "L'ID du compte est obligatoire")
    private Long compteId;

    @NotNull(message = "Le type est obligatoire")
    private TypeMouvement type;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    private String libelle;

    private Long projetId;  // Optionnel
}