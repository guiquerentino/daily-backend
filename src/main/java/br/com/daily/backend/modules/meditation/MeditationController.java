package br.com.daily.backend.modules.meditation;

import br.com.daily.backend.modules.meditation.domain.Meditation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/meditation")
public class MeditationController {

    @Autowired
    MeditationRepository repository;

    @GetMapping
    public List<Meditation> fetchAllMeditations(){
        return repository.findAll();
    }

}
