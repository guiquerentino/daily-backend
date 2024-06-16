package br.com.daily.backend.modules.scoreboard.domain.dto;

import br.com.daily.backend.modules.scoreboard.domain.Scoreboard;
import lombok.Data;

@Data
public class ScoreboardDTO {

    private int mentalHealth;
    private int focus;
    private int relationship;
    private int personalDevelopment;
    private int selfEsteem;
    private int stressControl;

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
