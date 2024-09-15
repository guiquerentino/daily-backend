package br.com.daily.backend.modules.accounts.domain.requests;

import br.com.daily.backend.modules.accounts.domain.enums.GENDER;
import br.com.daily.backend.modules.accounts.domain.enums.ROLE;

public record ChangeProfileRequest(
        String email,
        String name,
        GENDER gender,
        ROLE role,
        byte[] profilePhoto
) {
}
