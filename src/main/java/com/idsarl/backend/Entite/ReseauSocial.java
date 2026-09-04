package com.idsarl.backend.Entite;

import com.idsarl.backend.Enum.StatutReseauSocial;
import com.idsarl.backend.converters.AttributeEncryptor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reseaux_sociaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReseauSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String plateforme;

    @Column(nullable = false, length = 100)
    private String identifiant;

    @Column(nullable = false, length = 100)
    private String responsable;

    @Column(nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String motDePasse;  // Chiffré en base

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutReseauSocial statut;
}
