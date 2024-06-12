package br.com.daily.backend.modules.reminders;

import br.com.daily.backend.modules.reminders.domain.dto.ReminderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reminder")
public class ReminderController {

    @Autowired
    ReminderService service;

    @GetMapping
    public ResponseEntity<List<ReminderDTO>> fetchReminders(){
        return new ResponseEntity<>(service.fetchAllReminders(), HttpStatus.OK);
    }

}
