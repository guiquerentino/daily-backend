package br.com.daily.backend.modules.chat;

import br.com.daily.backend.modules.chat.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage message) {
        String destination = "/topic/chat/" + message.getPatientId() + "-" + message.getPsychologistId();
        messagingTemplate.convertAndSend(destination, message);
    }

}
