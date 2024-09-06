package br.com.daily.backend.modules.accounts.domain.enums;

public enum ROLE {

    PATIENT(0),
    PSYCHOLOGIST(1),
    EDITOR(2);

    public final int accountValue;

    ROLE(int value) {
        accountValue = value;
    }

}
