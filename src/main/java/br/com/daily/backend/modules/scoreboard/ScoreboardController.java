package br.com.daily.backend.modules.scoreboard;

import br.com.daily.backend.modules.scoreboard.domain.dto.ScoreboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scoreboard")
public class ScoreboardController {

    @Autowired
    ScoreboardService service;

    @GetMapping("{id}")
    public ResponseEntity<ScoreboardDTO> returnPersonalScoreboard (@PathVariable Long id){
        return new ResponseEntity<>(service.returnUserScoreboard(id), HttpStatus.OK);
    }
}
