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
@Table(name = "EMOTION_INFO")
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private String text;
    @Column(columnDefinition = "TEXT")
    private String tags;
    private LocalDateTime creationDate = LocalDateTime.now();
    private String comment;
    @Enumerated(EnumType.STRING)
    private EMOTION_TYPE emotionType;

    public static EmotionDTO mapToDTO(Emotion dObject) throws JsonProcessingException {
        EmotionDTO dto = new EmotionDTO();

        ObjectMapper mapper = new ObjectMapper();

        dto.setTags(mapper.readValue(dObject.getTags(), new TypeReference<List<Tag>>() {
        }));

        dto.setText(dObject.getText());
        dto.setId(dObject.getId());
        dto.setComment(dObject.getComment());
        dto.setOwnerId(dObject.getOwnerId());
        dto.setCreationDate(dObject.getCreationDate());
        dto.setEmotionType(dObject.getEmotionType());
        return dto;
    }


}
