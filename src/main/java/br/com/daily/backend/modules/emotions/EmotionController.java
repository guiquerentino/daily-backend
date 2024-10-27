package br.com.daily.backend.modules.emotions;

import br.com.daily.backend.modules.accounts.PatientRepository;
import br.com.daily.backend.modules.accounts.PsychologistRepository;
import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.Psychologist;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionCountDTO;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionRecord;
import br.com.daily.backend.modules.tags.domain.dto.TagDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/emotions")
public class EmotionController {

    @Autowired
    private EmotionService service;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmotionRepository emotionRepository;

    @Autowired
    private PsychologistRepository psychologistRepository;

    @PostMapping
    private ResponseEntity<EmotionRecord> saveRegister(@RequestBody @NotNull EmotionRecord request) throws JsonProcessingException {
        return new ResponseEntity<>(service.saveEmotion(request), HttpStatus.CREATED);
    }

    @GetMapping("/user/{ownerId}")
    private ResponseEntity<List<EmotionRecord>> fetchEmotionsByUser(@PathVariable Long ownerId, @RequestParam String date, @RequestParam boolean isPatient) {
        return new ResponseEntity<>(service.fetchEmotionsByUser(ownerId, date, isPatient), HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<EmotionRecord> updateRegister(@RequestBody EmotionRecord request) throws JsonProcessingException {
        return new ResponseEntity<>(service.updateEmotion(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteRegister(@PathVariable Long id) {
        service.deleteEmotion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    private ResponseEntity<List<EmotionCountDTO>> returnEmotionCountPerPsychologist(@RequestParam Long psychologistId) {
        Psychologist psychologist = psychologistRepository.findByUserId(psychologistId);
        List<Patient> patients = patientRepository.findByPsychologistId(psychologist.getId());

        Map<String, EmotionCountDTO> emotionCounts = new HashMap<>();

        String[] occurrences = {"diária", "semanal", "mensal", "anual"};
        for (String occurrence : occurrences) {
            EmotionCountDTO countDTO = new EmotionCountDTO();
            countDTO.setOcorrencia(occurrence);
            countDTO.setBravo(0);
            countDTO.setTriste(0);
            countDTO.setNormal(0);
            countDTO.setFeliz(0);
            countDTO.setMuitoFeliz(0);
            emotionCounts.put(occurrence, countDTO);
        }

        LocalDateTime now = LocalDateTime.now();
        for (Patient patient : patients) {
            Long userId = patient.getUser().getId();
            countEmotionsForPatient(userId, emotionCounts, now);
        }

        return new ResponseEntity<>(new ArrayList<>(emotionCounts.values()), HttpStatus.OK);
    }


    private void countEmotionsForPatient(Long userId, Map<String, EmotionCountDTO> emotionCounts, LocalDateTime now) {
        // Contagem diária
        LocalDateTime startOfDay = now.minusDays(1);
        List<Emotion> dailyEmotions = emotionRepository.findByUserIdAndCreatedAtBetween(userId, startOfDay, now);
        updateEmotionCounts(dailyEmotions, emotionCounts.get("diária"));

        // Contagem semanal
        LocalDateTime startOfWeek = now.minusWeeks(1);
        List<Emotion> weeklyEmotions = emotionRepository.findByUserIdAndCreatedAtBetween(userId, startOfWeek, now);
        updateEmotionCounts(weeklyEmotions, emotionCounts.get("semanal"));

        // Contagem mensal
        LocalDateTime startOfMonth = now.minusMonths(1);
        List<Emotion> monthlyEmotions = emotionRepository.findByUserIdAndCreatedAtBetween(userId, startOfMonth, now);
        updateEmotionCounts(monthlyEmotions, emotionCounts.get("mensal"));

        LocalDateTime startOfYear = now.minusYears(1);
        List<Emotion> yearlyEmotions = emotionRepository.findByUserIdAndCreatedAtBetween(userId, startOfYear, now);
        updateEmotionCounts(yearlyEmotions, emotionCounts.get("anual"));
    }

    private void updateEmotionCounts(List<Emotion> emotions, EmotionCountDTO countDTO) {
        for (Emotion emotion : emotions) {
            switch (emotion.getEmotionType()) {
                case BRAVO:
                    countDTO.setBravo(countDTO.getBravo() + 1);
                    break;
                case TRISTE:
                    countDTO.setTriste(countDTO.getTriste() + 1);
                    break;
                case NORMAL:
                    countDTO.setNormal(countDTO.getNormal() + 1);
                    break;
                case FELIZ:
                    countDTO.setFeliz(countDTO.getFeliz() + 1);
                    break;
                case MUITO_FELIZ:
                    countDTO.setMuitoFeliz(countDTO.getMuitoFeliz() + 1);
                    break;
            }
        }
    }

}
