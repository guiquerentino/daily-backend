package br.com.daily.backend.modules.chat.models;

import br.com.daily.backend.modules.accounts.domain.Psychologist;
import lombok.Data;

@Data
public class ChatPatientDTO {

    private Long id;
    private Psychologist psychologist;
    private String lastMessage;
    private String lastMessageTime;

}
