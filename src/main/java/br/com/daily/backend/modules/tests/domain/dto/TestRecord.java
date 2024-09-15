package br.com.daily.backend.modules.tests.domain.dto;

public record TestRecord(
        Long id,
        String title,
        String text,
        String bannerUrl,
        String questions
) {
}
