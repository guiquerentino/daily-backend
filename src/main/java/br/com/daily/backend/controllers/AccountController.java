package br.com.daily.backend.controllers;

import br.com.daily.backend.entities.Account;
import br.com.daily.backend.entities.dtos.AccountDTO;
import br.com.daily.backend.repositories.AccountRepository;
import br.com.daily.backend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {

    @Autowired
    public AccountService service;

    @PostMapping
    public ResponseEntity<Object> createAccount(@RequestBody AccountDTO request) {

        Account account = service.createAccount(request);

        return new ResponseEntity<>(account, HttpStatus.CREATED);

    }

//    @GetMapping(value = "{id}")
//    public ResponseEntity<Account> getAccountDetails(@PathVariable String accountId) {
//
//    }
//
//    @PutMapping
//    public ResponseEntity<Account> updateAccountDetails(@RequestBody Account request) {
//
//    }
//
//    @DeleteMapping(value = "{id}")
//    public ResponseEntity<Object> deleteAccount(@PathVariable String accountId) {
//
//    }

}
