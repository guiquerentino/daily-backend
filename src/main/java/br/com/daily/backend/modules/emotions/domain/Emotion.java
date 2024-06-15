package br.com.daily.backend.modules.emotions.domain;

import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
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
    private List<String> tags;
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
    @OneToMany(mappedBy = "emotion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public static EmotionDTO mapToDTO(Emotion dObject) {
        EmotionDTO dto = new EmotionDTO();

        dto.setTags(dObject.getTags());
        dto.setText(dObject.getText());
        dto.setId(dObject.getId());
        dto.setComments(dObject.getComments());
        dto.setOwnerId(dObject.getOwnerId());
        dto.setDataHoraCriacao(dObject.getDataHoraCriacao());

        return dto;
    }


}
