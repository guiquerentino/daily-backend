package br.com.daily.backend.modules.emotions.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "COMMENT_INFO")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "register_id", nullable = false)
    private Emotion emotion;
    private String text;
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
}
