package br.com.daily.backend.modules.emotions;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionRecord;
import br.com.daily.backend.modules.tags.domain.Tag;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
import br.com.daily.backend.modules.tags.domain.dto.TagDTO;
import br.com.daily.backend.modules.tags.TagRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    TagRepository tagRepository;

    public EmotionRecord saveEmotion(EmotionRecord request) throws JsonProcessingException {
        Emotion emotion = new Emotion();

        ObjectMapper mapper = new ObjectMapper();

        emotion.setEmotionType(request.emotionType());
        emotion.setText(request.text());
        emotion.setTags(mapper.writeValueAsString(request.tags()));
        emotion.setComment(request.comment());
        emotion.setUserId(request.userId());

        return Emotion.mapToRecord(repository.save(emotion));
    }

    public List<EmotionRecord> fetchEmotionsByUser(Long id, String date) {
        List<EmotionRecord> response = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedDate = LocalDateTime.parse(date, formatter).toLocalDate().atStartOfDay();

        LocalDateTime nextDay = formattedDate.plusDays(1);

        List<Emotion> fetchedDbEmotions = repository.findByUserIdAndCreatedAtBetween(id, formattedDate, nextDay);

        fetchedDbEmotions.forEach(emotion -> {
            try {
                response.add(Emotion.mapToRecord(emotion));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        return response;
    }

    public EmotionRecord updateEmotion(EmotionRecord request) throws JsonProcessingException {
        Optional<Emotion> emotionDB = repository.findById(request.id());
        ObjectMapper mapper = new ObjectMapper();

        if (emotionDB.isEmpty()) {
            throw new GenericException("EMOTION_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        Emotion emotion = emotionDB.get();

        emotion.setEmotionType(request.emotionType());
        emotion.setText(request.text());
        emotion.setTags(mapper.writeValueAsString(request.tags()));
        emotion.setComment(request.comment());
        emotion.setUserId(request.userId());

        return Emotion.mapToRecord(repository.save(emotion));
    }

    public void deleteEmotion(Long id) {
        Optional<Emotion> emotion = repository.findById(id);

        if (emotion.isEmpty()) {
            throw new GenericException("EMOTION_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
    }


}
