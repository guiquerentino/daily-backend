package br.com.daily.backend.modules.accounts.domain.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private String email;
    private String password;

}
