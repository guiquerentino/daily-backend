package br.com.daily.backend.modules.reminders;

import br.com.daily.backend.modules.reminders.domain.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {
}
