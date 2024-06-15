package br.com.daily.backend.modules.emotions;

import br.com.daily.backend.core.exceptions.LoginException;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmotionService {

    @Autowired
    EmotionRepository repository;

    public EmotionDTO saveEmotion(EmotionDTO request) {
        return Emotion.mapToDTO(repository.save(EmotionDTO.mapToDO(request)));
    }

    public List<EmotionDTO> fetchEmotionsByUser(Long id, String date) {
        List<EmotionDTO> response = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedDate = LocalDateTime.parse(date, formatter);

        LocalDateTime nextDay = formattedDate.plusDays(1);

        List<Emotion> fetchedDbEmotions = repository.findByOwnerIdAndDataHoraCriacaoBetween(id, formattedDate, nextDay);

        fetchedDbEmotions.forEach(emotion -> response.add(Emotion.mapToDTO(emotion)));

        return response;
    }

    public EmotionDTO updateEmotion(EmotionDTO request) {
        Optional<Emotion> emotion = repository.findById(request.getId());

        if (emotion.isEmpty()) {
            throw new LoginException("EMOTION_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        emotion.get().setOwnerId(request.getOwnerId());
        emotion.get().setText(request.getText());
        emotion.get().setTags(request.getTags());
        emotion.get().setComments(request.getComments());

        return Emotion.mapToDTO(repository.save(emotion.get()));
    }

    public void deleteEmotion(Long id) {
        Optional<Emotion> emotion = repository.findById(id);

        if (emotion.isEmpty()) {
            throw new LoginException("EMOTION_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
    }

}
