package br.com.daily.backend.services;

import br.com.daily.backend.entities.Reminder;
import br.com.daily.backend.entities.dtos.ReminderDTO;
import br.com.daily.backend.repositories.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderService {

    @Autowired
    ReminderRepository repository;

    public List<ReminderDTO> fetchAllReminders(){
        List<ReminderDTO> reminderDTOList = new ArrayList<>();
        for (Reminder reminder: repository.findAll()) {
            ReminderDTO reminderDTO = Reminder.mapToDTO(reminder);
            reminderDTOList.add(reminderDTO);
        }
        return reminderDTOList;
    }

    public Page<ReminderDTO> listAll(Pageable pageable){
        Reminder.mapToDTO(pageable);
        return repository.findAll(pageable);
    }
}
