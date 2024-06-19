package br.com.daily.backend.modules.accounts.domain.enums;

public enum TARGET {
    ANXIETY_CONTRROL(0), REDUCE_STRESS(1), IMPROVE_HUMOR(2), IMPROVE_CONFIDENCE(3), IMPROVE_FOCUS(4);

    public final int targetValue;

    TARGET(int value) {
        targetValue = value;
    }

}
