package com.idsarl.backend.dto.response;

import com.idsarl.backend.Enum.TypeMouvement;
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
public class MouvementBancaireResponse {

    private Long id;
    private Long compteId;
    private TypeMouvement type;
    private BigDecimal montant;
    private LocalDate date;
    private String libelle;
    private Long projetId;
}