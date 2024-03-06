package br.com.daily.backend.services;

import br.com.daily.backend.entities.Account;
import br.com.daily.backend.entities.dtos.AccountDTO;
import br.com.daily.backend.exceptions.LoginException;
import br.com.daily.backend.repositories.AccountRepository;
import br.com.daily.backend.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    public AccountRepository repository;

    @Autowired
    public AccountUtils utils;

    public AccountDTO createAccount(AccountDTO account) {

        Optional<Account> accountDB = repository.findByEmail(account.getEmail());

        if (accountDB.isPresent()) {
            throw new LoginException("ACCOUNT_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        }

        Account accountWithPasswordHashed = utils.hashPassword(account);
        repository.save(accountWithPasswordHashed);

        return Account.mapToDTO(accountWithPasswordHashed);
    }

    public AccountDTO authorizeAccount(AccountDTO account) {

        Optional<Account> accountDB = repository.findByEmail(account.getEmail());

        if (accountDB.isEmpty()) {
            throw new LoginException("EMAIL_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        byte[] password = utils.hashPasswordToAuthorize(account.getPassword(), accountDB.get().getPasswordSalt());

        if (Arrays.equals(password, accountDB.get().getPassword())) {

            account = Account.mapToDTO(accountDB.get());

            return account;

        }
        throw new LoginException("WRONG_PASSWORD", HttpStatus.UNAUTHORIZED);
    }

    public void changePassword(AccountDTO request) {

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
}
