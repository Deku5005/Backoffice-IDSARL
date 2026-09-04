package com.idsarl.backend.dto.response;

import com.idsarl.backend.Enum.TypePageContenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageContenuResponse {

    private Long id;
    private TypePageContenu type;
    private String titre;
    private String contenu;
    private LocalDateTime datePublication;
}