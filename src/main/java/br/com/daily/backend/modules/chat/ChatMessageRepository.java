package br.com.daily.backend.modules.chat;

import br.com.daily.backend.modules.chat.models.Chat;
import br.com.daily.backend.modules.chat.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatId(Long chatId);
}
