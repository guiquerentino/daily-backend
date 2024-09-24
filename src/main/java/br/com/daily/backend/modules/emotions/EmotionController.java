package br.com.daily.backend.modules.emotions;

import br.com.daily.backend.modules.emotions.domain.dto.EmotionDTO;
import br.com.daily.backend.modules.emotions.domain.dto.EmotionRecord;
import br.com.daily.backend.modules.tags.domain.dto.TagDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/emotions")
public class EmotionController {

    @Autowired
    private EmotionService service;

    @PostMapping
    private ResponseEntity<EmotionRecord> saveRegister(@RequestBody @NotNull EmotionRecord request) throws JsonProcessingException {
        return new ResponseEntity<>(service.saveEmotion(request), HttpStatus.CREATED);
    }

    @GetMapping("/user/{ownerId}")
    private ResponseEntity<List<EmotionRecord>> fetchEmotionsByUser(@PathVariable Long ownerId, @RequestParam String date) {
        return new ResponseEntity<>(service.fetchEmotionsByUser(ownerId, date), HttpStatus.OK);
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

}
