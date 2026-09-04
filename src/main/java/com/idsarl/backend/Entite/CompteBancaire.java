package com.idsarl.backend.Entite;

import com.idsarl.backend.converters.AttributeEncryptor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "comptes_bancaires")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String banque;

    @Column(nullable = false, unique = true)
    @Convert(converter = AttributeEncryptor.class)
    private String numero;  // Chiffré en base

    @Column(nullable = false, length = 10)
    private String devise;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal solde;
}

