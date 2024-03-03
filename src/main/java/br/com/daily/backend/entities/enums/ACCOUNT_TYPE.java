package br.com.daily.backend.entities.enums;

public enum ACCOUNT_TYPE {

    PATIENT(1),
    PSYCHOLOGIST(2);

    public final int accountValue;

    ACCOUNT_TYPE(int value) {
        accountValue = value;
    }

}
