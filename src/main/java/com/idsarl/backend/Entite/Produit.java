package com.idsarl.backend.Entite;


import com.idsarl.backend.Enum.StatutProduit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 50)
    private String technologie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutProduit statut;

    @Column(name = "historique_versions", columnDefinition = "TEXT")
    private String historiqueVersions;  // Stocké comme chaîne (ex: "1.0.0, 1.1.0, 2.0.0")
}
