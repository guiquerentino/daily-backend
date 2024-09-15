package br.com.daily.backend.modules.emotions;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import br.com.daily.backend.modules.emotions.domain.Tag;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
import br.com.daily.backend.modules.emotions.domain.dto.TagDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    public EmotionDTO saveEmotion(EmotionDTO request) throws JsonProcessingException {


        return Emotion.mapToDTO(repository.save(EmotionDTO.mapToDO(request)));
    }

    public List<EmotionDTO> fetchEmotionsByUser(Long id, String date) {
        List<EmotionDTO> response = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedDate = LocalDateTime.parse(date, formatter).toLocalDate().atStartOfDay();

        LocalDateTime nextDay = formattedDate.plusDays(1);

        List<Emotion> fetchedDbEmotions = repository.findByUserIdAndCreatedAtBetween(id, formattedDate, nextDay);

        fetchedDbEmotions.forEach(emotion -> {
            try {
                response.add(Emotion.mapToDTO(emotion));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        return response;
    }

    public EmotionDTO updateEmotion(EmotionDTO request) throws JsonProcessingException {
        Optional<Emotion> emotion = repository.findById(request.getId());

        if (emotion.isEmpty()) {
            throw new GenericException("EMOTION_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        return Emotion.mapToDTO(repository.save(emotion.get()));
    }

    public void deleteEmotion(Long id) {
        Optional<Emotion> emotion = repository.findById(id);

        if (emotion.isEmpty()) {
            throw new GenericException("EMOTION_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
    }

    public List<TagDTO> returnAllTags() {

        List<TagDTO> response = new ArrayList<>();
        tagRepository.findAll().forEach(tag -> response.add(Tag.mapToDTO(tag)));

        return response;
    }

}
