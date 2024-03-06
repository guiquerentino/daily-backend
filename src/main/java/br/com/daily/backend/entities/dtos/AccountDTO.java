package br.com.daily.backend.entities.dtos;

import br.com.daily.backend.entities.Account;
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
    private boolean canAttend;

    public static Account mapToDO(AccountDTO accountDTO) {
        Account account = new Account();

        account.setHashAlgorithm("Argon2Id");
        account.setProfessionalDocument(accountDTO.getProfessionalDocument());
        account.setAge(accountDTO.getAge());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setFullName(accountDTO.getFullName());
        account.setCanAttend(accountDTO.isCanAttend());
        account.setEmail(accountDTO.getEmail());
        account.setAccountType(accountDTO.getAccountType());

        return account;
    }

}
