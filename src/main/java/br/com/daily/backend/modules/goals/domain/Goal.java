package br.com.daily.backend.modules.goals.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "GOALS_INFO")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}
