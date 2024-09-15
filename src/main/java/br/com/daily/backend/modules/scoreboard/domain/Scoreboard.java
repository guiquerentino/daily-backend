package br.com.daily.backend.modules.scoreboard.domain;

import br.com.daily.backend.modules.scoreboard.domain.dto.ScoreboardDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "scoreboards")
public class Scoreboard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private int mentalHealth;
    private int focus;
    private int relationship;
    private int personalDevelopment;
    private int selfEsteem;
    private int stressControl;

    public static ScoreboardDTO mapToDTO(Scoreboard scoreboard){
        ScoreboardDTO scoreboardDTO = new ScoreboardDTO();

        scoreboardDTO.setMentalHealth(scoreboard.getMentalHealth());
        scoreboardDTO.setFocus(scoreboard.getFocus());
        scoreboardDTO.setRelationship(scoreboard.getRelationship());
        scoreboardDTO.setPersonalDevelopment(scoreboard.getPersonalDevelopment());
        scoreboardDTO.setSelfEsteem(scoreboard.getSelfEsteem());
        scoreboardDTO.setStressControl(scoreboard.getStressControl());

        return scoreboardDTO;
    }
}
