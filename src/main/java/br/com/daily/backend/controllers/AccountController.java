package br.com.daily.backend.controllers;

import br.com.daily.backend.entities.Account;
import br.com.daily.backend.entities.dtos.AccountDTO;
import br.com.daily.backend.repositories.AccountRepository;
import br.com.daily.backend.services.AccountService;
import jakarta.validation.constraints.NotNull;
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
    public ResponseEntity<Object> createAccount(@RequestBody @NotNull AccountDTO request) {
        return new ResponseEntity<>(service.createAccount(request), HttpStatus.CREATED);
    }

    @PostMapping(value = "/authorize")
    public ResponseEntity<Object> authorizeAccount(@RequestBody @NotNull AccountDTO request) {
        return new ResponseEntity<>(service.authorizeAccount(request), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/password")
    public ResponseEntity<Object> changeAccountPassword(@RequestBody @NotNull AccountDTO request) {
        service.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
