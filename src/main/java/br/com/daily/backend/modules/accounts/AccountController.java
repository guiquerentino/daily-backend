package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.modules.accounts.domain.Account;
import br.com.daily.backend.modules.accounts.domain.dto.*;
import jakarta.persistence.Id;
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
@RequestMapping(value = "/api/v1/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountService service;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @NotNull CreateAccountDTO request) {
        return new ResponseEntity<>(service.createAccount(request), HttpStatus.CREATED);
    }

    @PostMapping(value = "/authorize")
    @Transactional
    public ResponseEntity<AccountDTO> authorizeAccount(@RequestBody @NotNull LoginDTO request) {
        return new ResponseEntity<>(service.authorizeAccount(request), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/password")
    public ResponseEntity<Object> changeAccountPassword(@RequestBody @NotNull ChangePasswordDTO request) {
        service.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update-profile")
    public ResponseEntity<Object> changeAccountInfos(@RequestBody @NotNull ChangeAccountDTO request) {
        service.changeAccountInfos(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/onboarding")
    public ResponseEntity<AccountDTO> doOnboarding(@RequestBody @NotNull OnboardingDTO request) {
        return new ResponseEntity<>(service.doOnboarding(request), HttpStatus.OK);
    }


    @PutMapping(value = "/profile-photo")
    @Transactional
    public ResponseEntity<AccountDTO> changeProfilePhoto(@RequestParam BigInteger userId, @RequestBody Map<String, String> requestBody) {
        Optional<Account> account = accountRepository.findById(userId);

        if(account.isPresent()){
            Account acc = account.get();

            String base64Image = requestBody.get("image");
            if (base64Image != null && !base64Image.isEmpty()) {
                try {
                    byte[] photoBytes = Base64.getDecoder().decode(base64Image);
                    acc.setProfilePhoto(photoBytes);
                    accountRepository.save(acc);
                } catch (IllegalArgumentException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PutMapping(value = "/goals")
    public ResponseEntity<Object> completeGoal(@RequestParam String goal, @RequestParam BigInteger userId) {
        Optional<Account> account = accountRepository.findById(userId);

        if (account.isPresent()) {
            Account acc = account.get();
            List<String> goals = acc.getCompletedGoals();

            if (goals == null) {
                goals = new ArrayList<>();
            }

            goals.add(goal);
            acc.setCompletedGoals(goals);

            accountRepository.save(acc);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

