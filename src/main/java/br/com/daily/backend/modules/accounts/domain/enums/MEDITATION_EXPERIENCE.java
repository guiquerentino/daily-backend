package br.com.daily.backend.modules.accounts.domain.enums;

public enum MEDITATION_EXPERIENCE {
    REGULARLY(0),
    OCCASIONALLY(1),
    LONG_TIME(2),
    NEVER(3);

    public final int meditationExperienceValue;

    MEDITATION_EXPERIENCE(int value) {
        meditationExperienceValue = value;
    }

}
