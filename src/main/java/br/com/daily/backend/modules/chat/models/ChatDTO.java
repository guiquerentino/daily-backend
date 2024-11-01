package br.com.daily.backend.modules.chat.models;

import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.Psychologist;
import lombok.Data;

@Data
public class ChatDTO {

    private Long id;
    private Patient patient;
    private String lastMessage;
    private String lastMessageTime;

}
