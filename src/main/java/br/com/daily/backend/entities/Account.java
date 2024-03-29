package br.com.daily.backend.entities;

import java.math.BigInteger;

import br.com.daily.backend.entities.dtos.AccountDTO;
import br.com.daily.backend.entities.enums.ACCOUNT_TYPE;
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
    private String age;
    private String phoneNumber;
    private String personalDocument;
    private String professionalDocument;
    private String hashAlgorithm;
    private boolean canAttend;

    public static AccountDTO mapToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(account.getId());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setAge(account.getAge());
        accountDTO.setPhoneNumber(account.getPhoneNumber());
        accountDTO.setPersonalDocument(account.getPersonalDocument());
        accountDTO.setProfessionalDocument(account.getProfessionalDocument());
        accountDTO.setCanAttend(account.isCanAttend());

        return accountDTO;
    }
}
