package br.com.daily.backend.modules.emotions.domain.dto;

import lombok.Data;

@Data
public class EmotionCountDTO {

    public String ocorrencia;
    public int bravo;
    public int triste;
    public int normal;
    public int feliz;
    public int muitoFeliz;

}
