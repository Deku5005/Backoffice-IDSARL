package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.Hebergement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HebergementRepository extends JpaRepository<Hebergement, Long> {

    List<Hebergement> findByDateExpirationBefore(LocalDate date);

    // Hébergements expirant dans moins de X jours (alerte)
    @Query("SELECT h FROM Hebergement h WHERE h.dateExpiration BETWEEN :now AND :now + :days")
    List<Hebergement> findExpiringSoon(@Param("now") LocalDate now, @Param("days") int days);

    List<Hebergement> findByProduitId(Long produitId);
}