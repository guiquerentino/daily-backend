package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.MEDITATION_EXPERIENCE;
import br.com.daily.backend.modules.accounts.domain.enums.TARGET;
import lombok.Data;

import java.math.BigInteger;

@Data
public class OnboardingDTO {

    private BigInteger accountId;
    private String fullName;
    private GENDER gender;
    private int age;
    private TARGET target;
    private MEDITATION_EXPERIENCE meditationExperience;
}
