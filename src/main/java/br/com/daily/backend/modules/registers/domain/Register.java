package br.com.daily.backend.modules.registers.domain;

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
    private List<String> tags;
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}
