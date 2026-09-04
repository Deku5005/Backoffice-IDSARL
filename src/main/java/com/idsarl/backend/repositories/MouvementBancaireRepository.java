package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.MouvementBancaire;
import com.idsarl.backend.Enum.TypeMouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MouvementBancaireRepository extends JpaRepository<MouvementBancaire, Long> {

    List<MouvementBancaire> findByCompteId(Long compteId);

    List<MouvementBancaire> findByDateBetween(LocalDate debut, LocalDate fin);

    @Query("SELECT SUM(m.montant) FROM MouvementBancaire m WHERE m.compte.id = :compteId AND m.type = :type")
    BigDecimal sumMontantByCompteAndType(@Param("compteId") Long compteId, @Param("type") TypeMouvement type);
}