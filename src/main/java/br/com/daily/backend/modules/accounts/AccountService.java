package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.modules.accounts.domain.Account;
import br.com.daily.backend.modules.accounts.domain.dto.*;
import br.com.daily.backend.core.exceptions.LoginException;
import br.com.daily.backend.modules.code.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountUtils utils;

    @Autowired
    private CodeService codeService;

    public AccountDTO createAccount(CreateAccountDTO account) {

        Optional<Account> accountDB = repository.findByEmail(account.getEmail());

        if (accountDB.isPresent()) {
            throw new LoginException("ACCOUNT_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        }

        Account accountWithPasswordHashed = utils.hashPassword(account);

        repository.save(accountWithPasswordHashed);

        return Account.mapToDTO(accountWithPasswordHashed);
    }

    public AccountDTO authorizeAccount(LoginDTO account) {

        Optional<Account> accountDB = repository.findByEmail(account.getEmail());

        if (accountDB.isEmpty()) {
            throw new LoginException("EMAIL_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        if (!accountDB.get().getAccountType().equals(account.getAccountType())) {
            throw new LoginException("WRONG_APP", HttpStatus.CONFLICT);
        }

        byte[] password = utils.hashPasswordToAuthorize(account.getPassword(), accountDB.get().getPasswordSalt());

        if (Arrays.equals(password, accountDB.get().getPassword())) {
            return Account.mapToDTO(accountDB.get());
        }

        throw new LoginException("WRONG_PASSWORD", HttpStatus.UNAUTHORIZED);
    }

    public void changePassword(ChangePasswordDTO request) {
        Optional<Account> accountDB = repository.findByEmail(request.getEmail());

        if (accountDB.isPresent()) {

            Account account = utils.hashPassword(request);

            accountDB.get().setPassword(account.getPassword());
            accountDB.get().setPasswordSalt(account.getPasswordSalt());

            repository.save(accountDB.get());

            return;
        }
        throw new LoginException("ACCOUNT_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public void changeEmail(ChangeEmailDTO request) {
        Optional<Account> account = repository.findByEmail(request.getEmail());

        if (account.isPresent()) {

            account.get().setEmail(request.getNewEmail());

            repository.save(account.get());

            return;
        }
        throw new LoginException("ACCOUNT_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public AccountDTO doOnboarding(OnboardingDTO request) {
        Optional<Account> account = repository.findById(request.getAccountId());

        if (account.isPresent()) {

            if (!account.get().isHasOnboarding()) {

                account.get().setFullName(request.getFullName());
                account.get().setGender(request.getGender());
                account.get().setAge(request.getAge());
                account.get().setTarget(request.getTarget());
                account.get().setMeditationExperience(request.getMeditationExperience());
                account.get().setCodeToConnect(codeService.generateAccountCode());
                account.get().setHasOnboarding(true);

                repository.save(account.get());

                return Account.mapToDTO(account.get());
            }

            throw new LoginException("ONBOARDING_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);

        }
        throw new LoginException("ACCOUNT_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
