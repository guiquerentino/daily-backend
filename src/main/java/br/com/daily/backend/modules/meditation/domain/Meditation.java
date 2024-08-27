package br.com.daily.backend.modules.meditation.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "MEDITATION_INFO")
public class Meditation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String photoUrl;

    private String type;

    private String text;

}
