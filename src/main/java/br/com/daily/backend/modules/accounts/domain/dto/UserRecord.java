package br.com.daily.backend.modules.accounts.domain.dto;

import br.com.daily.backend.modules.accounts.domain.enums.MEDITATION_EXPERIENCE;
import br.com.daily.backend.modules.accounts.domain.enums.ROLE;
import br.com.daily.backend.modules.accounts.domain.enums.TARGET;

import java.util.List;

public record UserRecord(
        Long id,
        ROLE role,
        String email,
        boolean hasOnboarding) {

}
