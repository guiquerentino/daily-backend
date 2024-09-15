package br.com.daily.backend.modules.meditation.domain.dto;

public record MeditationRecord(
        Long id,
        String name,
        String photoUrl,
        String type,
        String text,
        String audioFile,
        String duration
) {
}
