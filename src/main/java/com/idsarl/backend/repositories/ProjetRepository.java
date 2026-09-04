package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.Projet;
import com.idsarl.backend.Enum.StatutProjet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    List<Projet> findByStatut(StatutProjet statut);

    List<Projet> findByDateFinBeforeAndStatutNot(LocalDate date, StatutProjet statut);

    @Query("SELECT SUM(p.montantApporte) FROM Projet p")
    BigDecimal sumMontantApporte();

    @Query("SELECT SUM(p.depenses) FROM Projet p")
    BigDecimal sumDepenses();

    @Query("SELECT COUNT(p) FROM Projet p WHERE p.dateFin < :date AND p.statut != 'TERMINE'")
    long countProjetsEnRetard(@Param("date") LocalDate date);

    @Query("SELECT p FROM Projet p WHERE p.dateFin < :date AND p.statut != 'TERMINE'")
    List<Projet> findProjetsEnRetard(@Param("date") LocalDate date);
}