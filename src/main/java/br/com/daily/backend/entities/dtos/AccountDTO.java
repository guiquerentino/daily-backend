package br.com.daily.backend.entities.dtos;

import br.com.daily.backend.entities.enums.ACCOUNT_TYPE;
import lombok.Data;

import java.math.BigInteger;

@Data
public class AccountDTO {
    private BigInteger id;
    private ACCOUNT_TYPE accountType;
    private String email;
    private String password;
    private String fullName;
    private String age;
    private String phoneNumber;
    private String personalDocument;
    private String professionalDocument;
    private String hashAlgorithm;
    private boolean canAttend;

}
