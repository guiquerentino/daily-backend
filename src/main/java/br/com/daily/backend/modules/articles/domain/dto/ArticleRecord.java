package br.com.daily.backend.modules.articles.domain.dto;

import java.time.LocalDateTime;

public record ArticleRecord(
        Long id,
        String title,
        String text,
        String autor,
        String bannerURL,
        String minutesToRead,
        String category,
        LocalDateTime createdAt
) {
}
