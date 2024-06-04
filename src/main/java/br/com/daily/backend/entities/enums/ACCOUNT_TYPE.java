package br.com.daily.backend.entities.enums;

public enum ACCOUNT_TYPE {

    PATIENT(0),
    PSYCHOLOGIST(1);

    public final int accountValue;

    ACCOUNT_TYPE(int value) {
        accountValue = value;
    }

}
