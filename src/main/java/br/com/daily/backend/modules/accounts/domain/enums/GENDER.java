package br.com.daily.backend.modules.accounts.domain.enums;

public enum GENDER {
    FEMALE(0), MALE(1), NONE(2);

    public final int genderValue;

    GENDER(int value) {
        genderValue = value;
    }

}
