package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.MEDITATION_EXPERIENCE;
import br.com.daily.backend.modules.accounts.domain.enums.TARGET;
import br.com.daily.backend.modules.emotions.domain.Emotion;
import lombok.Data;

import java.util.List;

@Data
public class PatientDTO {
    private Long id;
    private String name;
    private int age;
    private GENDER gender;
    private List<TARGET> targets;
    private byte[] profilePhoto;
    private MEDITATION_EXPERIENCE meditationExperience;
    private Emotion lastEmotion;

    public static PatientDTO from(Patient patient, Emotion lastEmotion) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        dto.setGender(patient.getGender());
        dto.setTargets(patient.getTargets());
        dto.setProfilePhoto(patient.getProfilePhoto());
        dto.setMeditationExperience(patient.getMeditationExperience());
        dto.setLastEmotion(lastEmotion);
        return dto;
    }

}
