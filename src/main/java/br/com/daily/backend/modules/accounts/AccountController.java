package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.modules.accounts.domain.dto.AccountDTO;
import br.com.daily.backend.modules.accounts.domain.dto.ChangePasswordDTO;
import br.com.daily.backend.modules.accounts.domain.dto.CreateAccountDTO;
import br.com.daily.backend.modules.accounts.domain.dto.LoginDTO;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountService service;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @NotNull CreateAccountDTO request) {
        return new ResponseEntity<>(service.createAccount(request), HttpStatus.CREATED);
    }

    @PostMapping(value = "/authorize")
    public ResponseEntity<AccountDTO> authorizeAccount(@RequestBody @NotNull LoginDTO request) {
        return new ResponseEntity<>(service.authorizeAccount(request), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/password")
    public ResponseEntity<Object> changeAccountPassword(@RequestBody @NotNull ChangePasswordDTO request) {
        service.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
