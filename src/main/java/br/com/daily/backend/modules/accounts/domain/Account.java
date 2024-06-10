package br.com.daily.backend.modules.accounts.domain;

import java.math.BigInteger;

import br.com.daily.backend.modules.accounts.domain.dto.AccountDTO;
import br.com.daily.backend.modules.accounts.domain.enums.ACCOUNT_TYPE;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USER_INFO")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private ACCOUNT_TYPE accountType;
    private String email;
    private byte[] password;
    private byte[] passwordSalt;
    private String fullName;
    private String hashAlgorithm;

    public static AccountDTO mapToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(account.getId());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFullName(account.getFullName());

        return accountDTO;
    }
}
