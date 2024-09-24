package br.com.daily.backend.modules.emotions.domain.dto;

import br.com.daily.backend.modules.emotions.domain.enums.EMOTION_TYPE;
import br.com.daily.backend.modules.tags.domain.Tag;

import java.time.LocalDateTime;
import java.util.List;

public record EmotionRecord(
        Long id,
        Long userId,
        String text,
        List<Tag> tags,
        LocalDateTime createdAt,
        String comment,
        EMOTION_TYPE emotionType
) {
}
