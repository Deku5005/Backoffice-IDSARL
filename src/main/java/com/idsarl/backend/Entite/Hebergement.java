package com.idsarl.backend.Entite;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hebergements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hebergement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String plateforme;

    @Column(nullable = false, length = 255)
    private String domaine;

    @Column(name = "date_expiration", nullable = false)
    private LocalDate dateExpiration;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal cout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id")
    private Produit produit;  // Optionnel
}
