package br.com.daily.backend.modules.accounts.domain.requests;

import java.math.BigInteger;

public record ChangeAccountRequest(BigInteger userId, String name, String email, int gender) {
}
