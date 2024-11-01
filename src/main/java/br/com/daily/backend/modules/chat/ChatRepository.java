package br.com.daily.backend.modules.chat;

import br.com.daily.backend.modules.chat.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByPsychologistId(String psychologistId);
    Chat findByPatientId(String patientId);

}
