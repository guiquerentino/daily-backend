package br.com.daily.backend.modules.scoreboard.domain.dto;

import br.com.daily.backend.modules.scoreboard.domain.Scoreboard;
import lombok.Data;

@Data
public class ScoreboardDTO {

    private int mentalHealth = 50;
    private int focus = 50;
    private int relationship = 50;
    private int personalDevelopment = 50;
    private int selfEsteem = 50;
    private int stressControl = 50;

    public static Scoreboard mapToDo(ScoreboardDTO scoreboardDTO){
        Scoreboard scoreboardDO = new Scoreboard();

        scoreboardDO.setMentalHealth(scoreboardDTO.getMentalHealth());
        scoreboardDO.setFocus(scoreboardDTO.getRelationship());
        scoreboardDO.setRelationship(scoreboardDTO.getRelationship());
        scoreboardDO.setPersonalDevelopment(scoreboardDTO.getPersonalDevelopment());
        scoreboardDO.setSelfEsteem(scoreboardDTO.getSelfEsteem());
        scoreboardDO.setStressControl(scoreboardDTO.getStressControl());

        return scoreboardDO;
    }
}
