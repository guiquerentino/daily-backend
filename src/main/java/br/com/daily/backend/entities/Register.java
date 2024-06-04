package br.com.daily.backend.entities;

import br.com.daily.backend.entities.enums.EMOTION_TYPE;
import br.com.daily.backend.entities.enums.WEATHER_TYPE;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "REGISTER_INFO")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private String text;
    private EMOTION_TYPE emotionType;
    private WEATHER_TYPE weatherType;
    private List<String> tags;
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}
