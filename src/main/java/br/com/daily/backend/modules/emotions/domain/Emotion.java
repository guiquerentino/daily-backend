package br.com.daily.backend.modules.emotions.domain;

import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionRecord;
import br.com.daily.backend.modules.emotions.domain.enums.EMOTION_TYPE;
import br.com.daily.backend.modules.tags.domain.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "emotions")
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "text", length = 100)
    private String text;

    @Column(columnDefinition = "TEXT", name = "tags")
    private String tags;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    private EMOTION_TYPE emotionType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true, updatable = true)
    private LocalDateTime deletedAt;

    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static EmotionRecord mapToRecord(Emotion emotion) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<Tag> tags = mapper.readValue(emotion.getTags(), new TypeReference<List<Tag>>() {
        });

        return new EmotionRecord(emotion.getId(), emotion.getUserId(), emotion.getText(), tags, emotion.getCreatedAt(), emotion.getComment(), emotion.getEmotionType());
    }


}
