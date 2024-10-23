package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.MEDITATION_EXPERIENCE;
import br.com.daily.backend.modules.accounts.domain.enums.ROLE;
import br.com.daily.backend.modules.accounts.domain.enums.TARGET;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {

    Long id;

    ROLE role;

    String email;

    boolean hasOnboarding;

    @JsonProperty(value = "fullName")
    String name;

    byte[] profilePhoto;

    List<TARGET> targetList;

    MEDITATION_EXPERIENCE meditationExperience;

    String connectionCode;

    List<PatientDTO> patients;

    GENDER gender;

    int age;

}
