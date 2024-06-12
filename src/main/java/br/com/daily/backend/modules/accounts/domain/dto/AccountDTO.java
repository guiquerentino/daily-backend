package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.Account;
import br.com.daily.backend.modules.accounts.domain.enums.ACCOUNT_TYPE;
import lombok.Data;

import java.math.BigInteger;

@Data
public class AccountDTO {

    private BigInteger id;
    private ACCOUNT_TYPE accountType;
    private String email;
    private String fullName;

    public static Account mapToDO(AccountDTO accountDTO) {
        Account account = new Account();

        account.setHashAlgorithm("Argon2Id");
        account.setFullName(accountDTO.getFullName());
        account.setEmail(accountDTO.getEmail());
        account.setAccountType(accountDTO.getAccountType());

        return account;
    }

}
