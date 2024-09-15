package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record UserRecord(
        Long id,
        ROLE role,
        String email,
        boolean hasOnboarding,
        @JsonIgnore
        byte[] password,
        @JsonIgnore
        byte[] passwordSalt) {

}
