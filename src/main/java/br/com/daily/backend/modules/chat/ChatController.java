package br.com.daily.backend.modules.chat;

import br.com.daily.backend.modules.accounts.PatientRepository;
import br.com.daily.backend.modules.accounts.PsychologistRepository;
import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.Psychologist;
import br.com.daily.backend.modules.chat.models.Chat;
import br.com.daily.backend.modules.chat.models.ChatDTO;
import br.com.daily.backend.modules.chat.models.ChatMessage;
import br.com.daily.backend.modules.chat.models.ChatPatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/chat")
public class ChatController {

    @Autowired
    ChatMessageRepository repository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PsychologistRepository psychologistRepository;

    @PostMapping
    public ChatMessage postMessage(@RequestBody ChatMessage message) {
        Optional<Chat> chat = chatRepository.findById(message.getChatId());

        if (chat.isPresent()) {
            chat.get().setLastMessage(message.getContent());

            LocalDateTime messageDateTime = LocalDateTime.now();
            LocalDateTime now = LocalDateTime.now();

            long daysDiff = ChronoUnit.DAYS.between(messageDateTime, now);
            long weeksDiff = daysDiff / 7;
            long monthsDiff = ChronoUnit.MONTHS.between(messageDateTime, now);

            String lastMessageTime;
            if (daysDiff == 0) {
                lastMessageTime = messageDateTime.toLocalTime().toString().substring(0, 5);
            } else if (daysDiff < 7) {
                lastMessageTime = "Há " + daysDiff + " dia" + (daysDiff > 1 ? "s" : "");
            } else if (weeksDiff < 4) {
                lastMessageTime = "Há " + weeksDiff + " semana" + (weeksDiff > 1 ? "s" : "");
            } else {
                lastMessageTime = "Há " + monthsDiff + " mês" + (monthsDiff > 1 ? "es" : "");
            }

            chat.get().setLastMessageTime(lastMessageTime);
            chatRepository.save(chat.get());
        }

        return repository.save(message);
    }

    @GetMapping(value = "/patient")
    public ChatPatientDTO getAllPatientChats(@RequestParam String patientId){
        Patient patient = patientRepository.findByUserId(Long.parseLong(patientId));

        Chat chat = chatRepository.findByPatientId(patient.getId().toString());

        ChatPatientDTO response = new ChatPatientDTO();

        response.setId(chat.getId());
        response.setPsychologist(psychologistRepository.findById(Long.parseLong(chat.getPsychologistId())).orElse(null));
        response.setLastMessage(chat.getLastMessage());
        response.setLastMessageTime(chat.getLastMessageTime());


        return response;
    }


    @GetMapping(value = "/psychologist")
    public List<ChatDTO> getAllPsycholigstChats(@RequestParam String psychologistId) {
        Psychologist psychologist = psychologistRepository.findByUserId(Long.parseLong(psychologistId));

        List<Chat> chats = chatRepository.findByPsychologistId(psychologist.getId().toString());

        List<ChatDTO> response = new ArrayList<>();
        for (Chat chat : chats) {
            ChatDTO chatDTO = new ChatDTO();
            chatDTO.setId(chat.getId());
            chatDTO.setLastMessage(chat.getLastMessage());
            chatDTO.setPatient(patientRepository.findById(Long.parseLong(chat.getPatientId())).orElse(null));
            chatDTO.setLastMessageTime(chat.getLastMessageTime());
            response.add(chatDTO);
        }

        response.sort((chat1, chat2) -> {
            if (chat1.getLastMessage() == null && chat2.getLastMessage() != null) {
                return 1;
            } else if (chat1.getLastMessage() != null && chat2.getLastMessage() == null) {
                return -1;
            } else if (chat1.getLastMessage() == null && chat2.getLastMessage() == null) {
                return 0;
            }
            return chat2.getLastMessageTime().compareTo(chat1.getLastMessageTime());
        });

        return response;
    }

    @GetMapping
    public List<ChatMessage> getAllMessagesById(@RequestParam Long chatId){
        return repository.findByChatId(chatId);
    }

}
