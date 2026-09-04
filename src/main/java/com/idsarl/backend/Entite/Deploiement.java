package com.idsarl.backend.Entite;



import com.idsarl.backend.Enum.Periodicite;
import com.idsarl.backend.Enum.StatutDeploiement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "deploiements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deploiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "date_deploiement", nullable = false)
    private LocalDate dateDeploiement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutDeploiement statut;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal montant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodicite periodicite;
}
