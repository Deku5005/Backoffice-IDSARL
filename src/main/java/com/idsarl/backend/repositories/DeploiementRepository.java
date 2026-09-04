package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.Deploiement;
import com.idsarl.backend.Enum.StatutDeploiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeploiementRepository extends JpaRepository<Deploiement, Long> {

    List<Deploiement> findByClientId(Long clientId);

    List<Deploiement> findByProduitId(Long produitId);

    List<Deploiement> findByStatut(StatutDeploiement statut);

    List<Deploiement> findByDateDeploiementBetween(LocalDate debut, LocalDate fin);

    @Query("SELECT SUM(d.montant) FROM Deploiement d WHERE d.statut = 'ACTIF'")
    BigDecimal sumMontantActif();

    @Query("SELECT COUNT(d) FROM Deploiement d WHERE d.dateDeploiement BETWEEN :debut AND :fin")
    long countByDateDeploiementBetween(@Param("debut") LocalDate debut, @Param("fin") LocalDate fin);
}