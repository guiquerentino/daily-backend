package br.com.daily.backend.modules.emotions.domain.dto;

import br.com.daily.backend.modules.emotions.domain.Comment;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmotionDTO {
    private Long id;
    private Long ownerId;
    private String text;
    private List<String> tags;
    private LocalDateTime dataHoraCriacao;
    private List<Comment> comments;

    public static Emotion mapToDO(EmotionDTO dto) {
        Emotion emotion = new Emotion();

        emotion.setTags(dto.getTags());
        emotion.setText(dto.getText());
        emotion.setId(dto.getId());
        emotion.setComments(dto.getComments());
        emotion.setOwnerId(dto.getOwnerId());

        return emotion;
    }

}
