package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.Account;
import br.com.daily.backend.modules.accounts.domain.enums.ACCOUNT_TYPE;
import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.MEDITATION_EXPERIENCE;
import br.com.daily.backend.modules.accounts.domain.enums.TARGET;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class AccountDTO {

    private BigInteger id;
    private ACCOUNT_TYPE accountType;
    private String email;
    private byte[] profilePhoto;

    private String fullName;
    private GENDER gender;
    private int age;
    private List<TARGET> target;
    private boolean hasOnboarding;
    private MEDITATION_EXPERIENCE meditationExperience;

    private String codeToConnect;


    public static Account mapToDO(AccountDTO accountDTO) {
        Account account = new Account();

        account.setHashAlgorithm("Argon2Id");
        account.setFullName(accountDTO.getFullName());
        account.setEmail(accountDTO.getEmail());
        account.setAccountType(accountDTO.getAccountType());
        account.setCodeToConnect(accountDTO.getCodeToConnect());
        account.setProfilePhoto(accountDTO.getProfilePhoto());

        return account;
    }

}
