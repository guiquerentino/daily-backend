package br.com.daily.backend.modules.accounts.domain.enums;

public enum TARGET {
    ANXIETY_CONTROL(0), REDUCE_STRESS(1), IMPROVE_HUMOR(2), IMPROVE_SLEEP(3),IMPROVE_CONFIDENCE(4), IMPROVE_FOCUS(5);

    public final int targetValue;

    TARGET(int value) {
        targetValue = value;
    }

}
