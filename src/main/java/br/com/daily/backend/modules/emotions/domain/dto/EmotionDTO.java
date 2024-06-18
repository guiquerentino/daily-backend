package br.com.daily.backend.modules.emotions.domain.dto;

import br.com.daily.backend.modules.emotions.domain.Comment;
import br.com.daily.backend.modules.emotions.domain.Tag;
import br.com.daily.backend.modules.emotions.domain.enums.EMOTION_TYPE;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmotionDTO {
    private Long id;
    private Long ownerId;
    private String text;
    private List<Tag> tags;
    private LocalDateTime creationDate;
    private List<Comment> comments;
    private EMOTION_TYPE emotionType;

    public static Emotion mapToDO(EmotionDTO dto) throws JsonProcessingException {
        Emotion emotion = new Emotion();

        ObjectMapper mapper = new ObjectMapper();

        emotion.setTags(mapper.writeValueAsString(dto.getTags()));
        emotion.setText(dto.getText());
        emotion.setId(dto.getId());
        emotion.setComments(dto.getComments());
        emotion.setOwnerId(dto.getOwnerId());
        emotion.setEmotionType(dto.getEmotionType());

        return emotion;
    }

}
