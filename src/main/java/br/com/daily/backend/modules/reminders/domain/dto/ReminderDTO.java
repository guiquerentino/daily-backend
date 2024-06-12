package br.com.daily.backend.modules.reminders.domain.dto;

import br.com.daily.backend.modules.reminders.domain.Reminder;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReminderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private LocalDateTime scheduledTime;

    public static Reminder mapToDO(ReminderDTO reminderDTO){
        Reminder reminder = new Reminder();

        reminder.setId(reminderDTO.getId());
        reminder.setText(reminderDTO.getText());
        reminder.setScheduledTime(reminderDTO.getScheduledTime());

        return reminder;
    }
}
