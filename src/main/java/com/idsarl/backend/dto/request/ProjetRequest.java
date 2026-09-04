package com.idsarl.backend.dto.request;

import com.idsarl.backend.Enum.StatutProjet;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
public class ProjetRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le statut est obligatoire")
    private StatutProjet statut;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    @Future(message = "La date de fin doit être dans le futur")
    private LocalDate dateFin;

    @NotNull(message = "Le montant apporté est obligatoire")
    @PositiveOrZero(message = "Le montant apporté doit être positif ou nul")
    private BigDecimal montantApporte;

    @NotNull(message = "Les dépenses sont obligatoires")
    @PositiveOrZero(message = "Les dépenses doivent être positives ou nulles")
    private BigDecimal depenses;
}