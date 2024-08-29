package br.com.daily.backend.modules.reminders.domain;

import br.com.daily.backend.modules.reminders.domain.dto.ReminderDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "REMINDER_INFO")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Long userId;
    private LocalDateTime scheduledTime;
    private boolean isActivated;

    public static ReminderDTO mapToDTO(Reminder reminder){
        ReminderDTO reminderDTO = new ReminderDTO();

        reminderDTO.setText(reminder.getText());
        reminderDTO.setScheduledTime(reminder.getScheduledTime());
        reminderDTO.setUserId(reminder.getUserId());
        reminderDTO.setActivated(reminder.isActivated());
        reminderDTO.setId(reminder.getId());

        return reminderDTO;
    }
}
