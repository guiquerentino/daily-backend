package br.com.daily.backend.modules.reminders;

import br.com.daily.backend.modules.reminders.domain.Reminder;
import br.com.daily.backend.modules.reminders.domain.dto.ReminderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/reminder")
public class ReminderController {

    @Autowired
    ReminderService service;

    @Autowired
    ReminderRepository repository;

    @GetMapping
    public ResponseEntity<List<ReminderDTO>> fetchReminders(@RequestParam Long userId){
        return new ResponseEntity<>(service.fetchAllReminders(userId), HttpStatus.OK);
    }

    @PostMapping
    public Reminder createReminder(@RequestBody ReminderDTO request, @RequestParam Long userId){
        request.setUserId(userId);
        return repository.save(ReminderDTO.mapToDO(request));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteReminder(@RequestParam Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
