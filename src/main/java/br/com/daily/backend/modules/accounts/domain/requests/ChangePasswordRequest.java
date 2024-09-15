package br.com.daily.backend.modules.accounts.domain.requests;

public record ChangePasswordRequest(String email, String newPassword) {
}
