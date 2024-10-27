package br.com.daily.backend.modules.chat.models;

import lombok.Data;

@Data
public class ChatMessage {

    private String sender;
    private String content;
    private String patientId;
    private String psychologistId;

}
