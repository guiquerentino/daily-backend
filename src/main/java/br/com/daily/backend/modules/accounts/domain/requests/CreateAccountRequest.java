package br.com.daily.backend.modules.accounts.domain.requests;

import br.com.daily.backend.modules.accounts.domain.enums.ROLE;

public record CreateAccountRequest(String email, String password, ROLE role) {
}
