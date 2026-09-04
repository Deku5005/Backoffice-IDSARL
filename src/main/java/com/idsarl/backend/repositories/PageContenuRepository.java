package com.idsarl.backend.repositories;

import com.idsarl.backend.Entite.PageContenu;
import com.idsarl.backend.Enum.TypePageContenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageContenuRepository extends JpaRepository<PageContenu, Long> {

    Optional<PageContenu> findByType(TypePageContenu type);

    List<PageContenu> findByTypeIn(List<TypePageContenu> types);
}