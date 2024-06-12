package br.com.daily.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "GOALS_INFO")
public class Goals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    


}
