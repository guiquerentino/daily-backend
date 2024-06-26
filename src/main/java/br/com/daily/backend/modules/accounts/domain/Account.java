package br.com.daily.backend.modules.accounts.domain;

import java.math.BigInteger;
import java.security.PrivilegedAction;
import java.util.List;

import br.com.daily.backend.modules.accounts.domain.dto.AccountDTO;
import br.com.daily.backend.modules.accounts.domain.dto.OnboardingDTO;
import br.com.daily.backend.modules.accounts.domain.enums.ACCOUNT_TYPE;
import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.MEDITATION_EXPERIENCE;
import br.com.daily.backend.modules.accounts.domain.enums.TARGET;
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
    private String hashAlgorithm;

    private String fullName;
    @Enumerated(EnumType.STRING)
    private GENDER gender;
    private int age;

    private boolean hasOnboarding;
    @Enumerated(EnumType.STRING)
    private TARGET target;
    @Enumerated(EnumType.STRING)
    private MEDITATION_EXPERIENCE meditationExperience;


    @Lob
    private byte[] profilePhoto;
    private String codeToConnect;

    public static AccountDTO mapToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(account.getId());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setProfilePhoto(account.getProfilePhoto());
        accountDTO.setCodeToConnect(account.getCodeToConnect());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setGender(account.getGender());
        accountDTO.setAge(account.getAge());
        accountDTO.setTarget(account.getTarget());
        accountDTO.setMeditationExperience(account.getMeditationExperience());

        return accountDTO;
    }
}
