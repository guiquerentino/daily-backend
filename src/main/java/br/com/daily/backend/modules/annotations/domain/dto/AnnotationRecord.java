package br.com.daily.backend.modules.annotations.domain.dto;

import java.time.LocalDateTime;

public record AnnotationRecord(
        Long id,
        Long userId,
        String text,
        LocalDateTime createdAt
) {
}
