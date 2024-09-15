package br.com.daily.backend.modules.goals.domain.dto;

import java.time.LocalDateTime;

public record GoalRecord(
        Long id,
        Long userId,
        String title,
        String createdBy,
        LocalDateTime createdAt,
        LocalDateTime scheduledTime,
        boolean isAllDay,
        boolean isCompleted
) {
}
