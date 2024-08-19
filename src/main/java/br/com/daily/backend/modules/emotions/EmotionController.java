package br.com.daily.backend.modules.emotions;

import br.com.daily.backend.BackendApplication;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
import br.com.daily.backend.modules.emotions.domain.dto.TagDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/emotions")
public class EmotionController {

    @Autowired
    private EmotionService service;

    @PostMapping
    private ResponseEntity<EmotionDTO> saveRegister(@RequestBody @NotNull EmotionDTO request) throws JsonProcessingException {
        return new ResponseEntity<>(service.saveEmotion(request), HttpStatus.CREATED);
    }

    @GetMapping("/user/{ownerId}")
    private ResponseEntity<List<EmotionDTO>> fetchEmotionsByUser(@PathVariable Long ownerId, @RequestParam String date) {
        return new ResponseEntity<>(service.fetchEmotionsByUser(ownerId, date), HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<EmotionDTO> updateRegister(@RequestBody EmotionDTO request) throws JsonProcessingException {
        return new ResponseEntity<>(service.updateEmotion(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteRegister(@PathVariable Long id) {
        service.deleteEmotion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/tags")
    private ResponseEntity<List<TagDTO>> returnAllTags(){
        return new ResponseEntity<>(service.returnAllTags(), HttpStatus.OK);
    }

}
