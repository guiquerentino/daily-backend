package br.com.daily.backend.modules.emotions.domain;

import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
import br.com.daily.backend.modules.emotions.domain.enums.EMOTION_TYPE;
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

    public static EmotionDTO mapToDTO(Emotion dObject) throws JsonProcessingException {
        EmotionDTO dto = new EmotionDTO();

        ObjectMapper mapper = new ObjectMapper();

        dto.setTags(mapper.readValue(dObject.getTags(), new TypeReference<List<Tag>>() {
        }));

        dto.setText(dObject.getText());
        dto.setId(dObject.getId());
        dto.setComment(dObject.getComment());
        dto.setUserId(dObject.getUserId());
        dto.setCreatedAt(dObject.getCreatedAt());
        dto.setEmotionType(dObject.getEmotionType());
        return dto;
    }


}
