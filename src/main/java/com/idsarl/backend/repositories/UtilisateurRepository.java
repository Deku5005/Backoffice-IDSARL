package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.Utilisateur;
import com.idsarl.backend.Enum.StatutUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Utilisateur> findByStatut(StatutUtilisateur statut);
}