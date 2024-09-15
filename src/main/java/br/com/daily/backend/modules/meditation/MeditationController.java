package br.com.daily.backend.modules.meditation;

import br.com.daily.backend.modules.meditation.domain.Meditation;
import br.com.daily.backend.modules.meditation.domain.dto.MeditationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meditation")
public class MeditationController {

    @Autowired
    MeditationService service;

    @PostMapping
    public ResponseEntity<MeditationRecord> createMeditation(@RequestBody MeditationRecord meditation) {
        return new ResponseEntity<>(service.createMeditation(meditation), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MeditationRecord>> fetchAllMeditations() {
        return new ResponseEntity<>(service.fetchAllMeditations(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MeditationRecord> updateMeditation(@RequestBody MeditationRecord meditation) {
        return new ResponseEntity<>(service.updateMeditation(meditation), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<MeditationRecord> deleteMeditation(@RequestParam Long id) {
        service.deleteMeditation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
