package br.com.daily.backend.services;

import br.com.daily.backend.entities.Account;
import br.com.daily.backend.entities.dtos.AccountDTO;
import br.com.daily.backend.repositories.AccountRepository;
import br.com.daily.backend.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    public AccountRepository repository;

    @Autowired
    public AccountUtils utils;

    public Account createAccount(AccountDTO account) {

        Optional<Account> accountDB = repository.findByEmail(account.getEmail());

        if (accountDB.isPresent()) {
            return null;
        }

        Account response = new Account();



        account = utils.hashPassword(account);
    }

}
