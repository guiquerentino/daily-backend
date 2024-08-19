package br.com.daily.backend.modules.scoreboard;

import br.com.daily.backend.modules.emotions.EmotionRepository;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import br.com.daily.backend.modules.emotions.domain.enums.EMOTION_TYPE;
import br.com.daily.backend.modules.scoreboard.domain.dto.ScoreboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreboardService {

    @Autowired
    EmotionRepository repository;

    public ScoreboardDTO returnUserScoreboard(Long id){
            ScoreboardDTO response = new ScoreboardDTO();

        // Pega todos os registros de emoções
        List<Emotion> emotions = repository.findByOwnerId(id);

        for(Emotion emotion: emotions){

            if(emotion.getEmotionType().equals(EMOTION_TYPE.MUITO_FELIZ)){
                response.setFocus(response.getFocus() + 10);
                response.setRelationship(response.getRelationship() + 10);
                response.setMentalHealth(response.getMentalHealth() + 10);
                response.setStressControl(response.getStressControl() + 10);
                response.setPersonalDevelopment(response.getPersonalDevelopment() + 10);
                response.setSelfEsteem(response.getSelfEsteem() + 10);
            }

            if(emotion.getEmotionType().equals(EMOTION_TYPE.FELIZ)){
                response.setFocus(response.getFocus() + 5);
                response.setRelationship(response.getRelationship() + 5);
                response.setMentalHealth(response.getMentalHealth() + 5);
                response.setStressControl(response.getStressControl() + 5);
                response.setPersonalDevelopment(response.getPersonalDevelopment() + 5);
                response.setSelfEsteem(response.getSelfEsteem() + 5);
            }

            if(emotion.getEmotionType().equals(EMOTION_TYPE.NORMAL)){
                response.setFocus(response.getFocus() + 0);
                response.setRelationship(response.getRelationship() + 0);
                response.setMentalHealth(response.getMentalHealth() + 0);
                response.setStressControl(response.getStressControl() + 0);
                response.setPersonalDevelopment(response.getPersonalDevelopment() + 0);
                response.setSelfEsteem(response.getSelfEsteem() + 0);
            }

            if(emotion.getEmotionType().equals(EMOTION_TYPE.TRISTE)){
                response.setFocus(response.getFocus() - 5);
                response.setRelationship(response.getRelationship() + 5);
                response.setMentalHealth(response.getMentalHealth() + 5);
                response.setStressControl(response.getStressControl() + 5);
                response.setPersonalDevelopment(response.getPersonalDevelopment() + 5);
                response.setSelfEsteem(response.getSelfEsteem() + 5);
            }

            if(emotion.getEmotionType().equals(EMOTION_TYPE.BRAVO)){
                response.setFocus(response.getFocus() - 10);
                response.setRelationship(response.getRelationship() - 7);
                response.setMentalHealth(response.getMentalHealth() - 8);
                response.setStressControl(response.getStressControl() - 6);
                response.setPersonalDevelopment(response.getPersonalDevelopment() - 6);
                response.setSelfEsteem(response.getSelfEsteem() - 4);
            }
        }

        return response;

    }

}
