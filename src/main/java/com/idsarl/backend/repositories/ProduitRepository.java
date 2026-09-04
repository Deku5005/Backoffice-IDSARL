package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.Produit;
import com.idsarl.backend.Enum.StatutProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByStatut(StatutProduit statut);

    List<Produit> findByTechnologie(String technologie);

    boolean existsByNom(String nom);
}