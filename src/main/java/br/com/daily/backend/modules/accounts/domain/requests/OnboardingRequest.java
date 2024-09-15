package br.com.daily.backend.modules.accounts.domain.requests;

import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.MEDITATION_EXPERIENCE;
import br.com.daily.backend.modules.accounts.domain.enums.TARGET;

import java.util.List;

public record OnboardingRequest(
        String name,
        GENDER gender,
        int age,
        List<TARGET> targets,
        MEDITATION_EXPERIENCE meditationExperience
) {
}
