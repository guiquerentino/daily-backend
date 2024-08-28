package br.com.daily.backend.modules.accounts.domain;

import java.math.BigInteger;
import java.security.PrivilegedAction;
import java.util.ArrayList;
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
@Table(name = "user_info")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private ACCOUNT_TYPE accountType;
    @Column(unique = true, nullable = false, name = "email")
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
    private List<TARGET> target;
    @Enumerated(EnumType.STRING)
    private MEDITATION_EXPERIENCE meditationExperience;
    private byte[] profilePhoto;
    private List<String> completedGoals = new ArrayList<>();
    private String codeToConnect;

    public static AccountDTO mapToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(account.getId());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setProfilePhoto(account.getProfilePhoto());
        accountDTO.setCompletedGoals(account.getCompletedGoals());
        accountDTO.setCodeToConnect(account.getCodeToConnect());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setGender(account.getGender());
        accountDTO.setAge(account.getAge());
        accountDTO.setTarget(account.getTarget());
        accountDTO.setMeditationExperience(account.getMeditationExperience());

        return accountDTO;
    }
}
