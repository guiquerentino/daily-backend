package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.modules.accounts.domain.dto.UserRecord;
import br.com.daily.backend.modules.accounts.domain.requests.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class AccountController {

    @Autowired
    public AccountService service;

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping
    public ResponseEntity<UserRecord> createAccount(@RequestBody @NotNull CreateAccountRequest request) {
        return new ResponseEntity<>(service.createAccount(request), HttpStatus.CREATED);
    }

    @PostMapping(value = "/authorize")
    @Transactional
    public ResponseEntity<UserRecord> authorizeAccount(@RequestBody @NotNull LoginRequest request) {
        return new ResponseEntity<>(service.authorizeAccount(request), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/password")
    public ResponseEntity<Object> changeAccountPassword(@RequestBody @NotNull ChangePasswordRequest request) {
        service.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update-profile")
    public ResponseEntity<Object> updateProfileAccount(@RequestBody @NotNull ChangeProfileRequest request, @RequestParam Long userId){
        service.updateProfile(request, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/onboarding/patient")
    public ResponseEntity<Object> doAccountOnboarding(@RequestBody @NotNull OnboardingRequest request, @RequestParam Long userId){
        service.finishPatientOnboarding(request, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

