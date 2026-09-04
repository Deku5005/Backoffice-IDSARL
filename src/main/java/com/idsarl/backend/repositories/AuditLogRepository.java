package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByUtilisateurId(Long utilisateurId);

    List<AuditLog> findByModule(String module);

    List<AuditLog> findByDateActionBetween(LocalDateTime debut, LocalDateTime fin);

    @Query("SELECT COUNT(a) FROM AuditLog a WHERE a.module = :module AND a.dateAction >= :since")
    long countByModuleSince(@Param("module") String module, @Param("since") LocalDateTime since);
}