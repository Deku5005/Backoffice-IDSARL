package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.ReseauSocial;
import com.idsarl.backend.Enum.StatutReseauSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReseauSocialRepository extends JpaRepository<ReseauSocial, Long> {

    List<ReseauSocial> findByStatut(StatutReseauSocial statut);

    boolean existsByPlateformeAndIdentifiant(String plateforme, String identifiant);
}