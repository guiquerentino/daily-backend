package br.com.daily.backend.modules.reminders;

import br.com.daily.backend.modules.reminders.domain.Reminder;
import br.com.daily.backend.modules.reminders.domain.dto.ReminderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderService {

    @Autowired
    ReminderRepository repository;

    public List<ReminderDTO> fetchAllReminders(Long userId){
        List<ReminderDTO> reminderDTOList = new ArrayList<>();
        for (Reminder reminder: repository.findByUserId(userId)) {
            ReminderDTO reminderDTO = Reminder.mapToDTO(reminder);
            reminderDTOList.add(reminderDTO);
        }
        return reminderDTOList;
    }


}
