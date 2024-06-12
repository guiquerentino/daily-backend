package br.com.daily.backend.modules.accounts.domain.dto;

import lombok.Data;

@Data
public class ChangeEmailDTO {

    private String email;
    private String newEmail;
    private String password;
}
