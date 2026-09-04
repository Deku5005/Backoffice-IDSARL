package com.idsarl.backend.aspect;

import com.idsarl.backend.Entite.AuditLog;
import com.idsarl.backend.repositories.AuditLogRepository;
import com.idsarl.backend.security.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    private static final Logger log = LoggerFactory.getLogger(AuditAspect.class);

    private final AuditLogRepository auditLogRepository;
    private final SecurityUtils securityUtils;

    public AuditAspect(AuditLogRepository auditLogRepository, SecurityUtils securityUtils) {
        this.auditLogRepository = auditLogRepository;
        this.securityUtils = securityUtils;
    }

    @Before("execution(* com.idsarl.backend.services.CompteBancaireService.get*(..)) || " +
            "execution(* com.idsarl.backend.services.CompteBancaireService.findAll*(..)) || " +
            "execution(* com.idsarl.backend.services.MouvementBancaireService.get*(..)) || " +
            "execution(* com.idsarl.backend.services.MouvementBancaireService.findAll*(..))")
    public void auditReadBank(JoinPoint joinPoint) {
        saveAuditLog("CONSULTATION_COMPTE", "BANK", getMethodName(joinPoint));
    }

    @AfterReturning("execution(* com.idsarl.backend.services.CompteBancaireService.save*(..)) || " +
            "execution(* com.idsarl.backend.services.CompteBancaireService.update*(..)) || " +
            "execution(* com.idsarl.backend.services.CompteBancaireService.delete*(..)) || " +
            "execution(* com.idsarl.backend.services.MouvementBancaireService.save*(..)) || " +
            "execution(* com.idsarl.backend.services.MouvementBancaireService.update*(..)) || " +
            "execution(* com.idsarl.backend.services.MouvementBancaireService.delete*(..))")
    public void auditWriteBank(JoinPoint joinPoint) {
        String action = joinPoint.getSignature().getName().startsWith("save") ? "CREATION_COMPTE" :
                joinPoint.getSignature().getName().startsWith("update") ? "MODIFICATION_COMPTE" :
                        "SUPPRESSION_COMPTE";
        saveAuditLog(action, "BANK", getMethodName(joinPoint));
    }

    private void saveAuditLog(String action, String module, String details) {
        try {
            String username = securityUtils.getCurrentUsername();
            Long userId = securityUtils.getCurrentUserId();

            AuditLog auditLog = new AuditLog();
            auditLog.setUtilisateurId(userId);
            auditLog.setUtilisateurNom(username);
            auditLog.setAction(action);
            auditLog.setModule(module);
            auditLog.setDetails(details);
            auditLog.setDateAction(LocalDateTime.now());

            auditLogRepository.save(auditLog);
        } catch (Exception e) {
            log.error("Erreur lors de l'enregistrement de l'audit", e);
        }
    }

    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getName() + "(" + String.join(", ",
                signature.getParameterNames()) + ")";
    }
}