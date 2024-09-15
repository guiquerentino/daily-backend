package br.com.daily.backend.modules.meditation;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.meditation.domain.Meditation;
import br.com.daily.backend.modules.meditation.domain.dto.MeditationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeditationService {

    @Autowired
    MeditationRepository repository;

    public List<MeditationRecord> fetchAllMeditations() {
        List<Meditation> meditations = repository.findAll();
        List<MeditationRecord> response = new ArrayList<>();

        for (Meditation meditation : meditations) {
            response.add(Meditation.mapToRecord(meditation));
        }

        return response;
    }

    public MeditationRecord createMeditation(MeditationRecord meditation) {
        Meditation meditationDB = new Meditation();

        meditationDB.setText(meditation.text());
        meditationDB.setName(meditation.name());
        meditationDB.setDuration(meditation.duration());
        meditationDB.setAudioFile(meditation.audioFile());
        meditationDB.setName(meditation.name());

        return Meditation.mapToRecord(repository.save(meditationDB));
    }

    public MeditationRecord updateMeditation(MeditationRecord meditation) {
        Optional<Meditation> meditationDB = repository.findById(meditation.id());

        if (meditationDB.isPresent()) {
            Meditation presentMeditation = meditationDB.get();

            presentMeditation.setText(meditation.text());
            presentMeditation.setName(meditation.name());
            presentMeditation.setDuration(meditation.duration());
            presentMeditation.setAudioFile(meditation.audioFile());
            presentMeditation.setName(meditation.name());

            return Meditation.mapToRecord(presentMeditation);
        }

        throw new GenericException("MEDITATION_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public void deleteMeditation(Long id) {
        Optional<Meditation> meditationDB = repository.findById(id);

        if (meditationDB.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new GenericException("MEDITATION_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }

}
